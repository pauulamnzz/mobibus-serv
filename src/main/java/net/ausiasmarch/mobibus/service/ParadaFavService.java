package net.ausiasmarch.mobibus.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import net.ausiasmarch.mobibus.entity.ParadaFavEntity;
import net.ausiasmarch.mobibus.entity.UserEntity;
import net.ausiasmarch.mobibus.exception.ResourceNotFoundException;
import net.ausiasmarch.mobibus.repository.ParadaFavRepository;
import net.ausiasmarch.mobibus.repository.UserRepository;

@Service
public class ParadaFavService {

    private static final String API_URL = "https://valencia.opendatasoft.com/api/explore/v2.1/catalog/datasets/emt/records";

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    @Autowired
    ParadaFavRepository oParadaFavRepository;

    @Autowired
    UserRepository userRepository;

    public ParadaFavEntity get(Long id) {
        return oParadaFavRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Thread not found"));
    }

    public ResponseEntity<?> create(ParadaFavEntity nuevaParadaFav) throws java.io.IOException {
        Long idParada = nuevaParadaFav.getId_parada_api();
        if (checkIdExists(idParada)) {
            oParadaFavRepository.save(nuevaParadaFav);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public void delete(Long id) {
        ParadaFavEntity oParadaFavEntity = oParadaFavRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parada favorita no encontrada con ID: " + id));
        oParadaFavEntity.getUsers().clear();
        oParadaFavRepository.save(oParadaFavEntity);
        oParadaFavRepository.deleteById(id);
    }

/*public ParadaFavEntity update(ParadaFavEntity oParadaFavEntityToSet) {
    
    // Verifica los permisos del usuario actual.
    oSessionService.onlyAdminsOrUsersWithIisOwnData(oSessionService.getSessionUser().getId());
    
    // Si el usuario actual es un usuario normal, actualiza la entidad con la lista que contiene al usuario de la sesión actual.
    if (oSessionService.isUser()) {
        List<UserEntity> userList = new ArrayList<>();
        userList.add(oSessionService.getSessionUser());
        oParadaFavEntityToSet.setUsers(userList);

        return oParadaFavRepository.save(oParadaFavEntityToSet);
    } else {
        // Si el usuario no es un usuario normal (presumiblemente un administrador), actualiza la entidad.
        return oParadaFavRepository.save(oParadaFavEntityToSet);
    }
} */

    

public ParadaFavEntity update(ParadaFavEntity updatedParadaFavEntity) {
    ParadaFavEntity existingParadaFavEntity = oParadaFavRepository.findById(updatedParadaFavEntity.getId()).orElse(null);
    if (existingParadaFavEntity != null) {
        // Actualizar la denominación y el id_parada_api
        existingParadaFavEntity.setDenominacion(updatedParadaFavEntity.getDenominacion());
        
        existingParadaFavEntity.setId_parada_api(updatedParadaFavEntity.getId_parada_api());
        List<UserEntity> newUsers = updatedParadaFavEntity.getUsers();

        // Si la lista de usuarios no está vacía, actualizar la lista de usuarios de la entidad existente
        if (newUsers != null && !newUsers.isEmpty()) {
            // Obtener el primer usuario del JSON (si hay más de uno, ajusta según tus necesidades)
            UserEntity newUser = newUsers.get(0);
            // Verificar si el usuario existe antes de agregarlo
            UserEntity existingUser = userRepository.findById(newUser.getId()).orElse(null);
            if (existingUser != null) {
                existingParadaFavEntity.getUsers().clear();
                existingParadaFavEntity.getUsers().add(existingUser);
            }
        }
        return oParadaFavRepository.save(existingParadaFavEntity);
    } else {
        // No se encontró la entidad en la base de datos
        return null;
    }
}
    




    /**
     * Verifica si un identificador dado existe en la API mediante una solicitud
     * HTTP.
     *
     * @param id El identificador a verificar en la API.
     * @return true si el identificador existe en la API, false en caso contrario.
     * @throws java.io.IOException
     */
    public boolean checkIdExists(Long id) throws java.io.IOException {
        try {
            // Construir la URL
            String api_url = API_URL + "?where=id_parada=" + id + "&limit=1";
            Logger.getLogger(getClass().getName()).info("API URL: " + api_url);

            // Crear una instancia de HttpClient para realizar la solicitud HTTP
            HttpClient oHttpClient = HttpClient.newHttpClient();

            // Construir la solicitud HTTP GET
            HttpRequest oHttpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(api_url))
                    .build();

            // Enviar la solicitud HTTP y obtener la respuesta
            HttpResponse<String> oHttpResponse = oHttpClient.send(oHttpRequest, HttpResponse.BodyHandlers.ofString());
            Logger.getLogger(getClass().getName()).info("API Response: " + oHttpResponse.body());

            // Verificar si la respuesta es exitosa (código 2xx) y si contiene datos
            if (oHttpResponse.statusCode() == HttpStatus.OK.value()) {
                // Convertir la respuesta JSON a un objeto para analizar el valor de total_count
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(oHttpResponse.body());

                // Obtener el valor de total_count desde la respuesta JSON
                int totalCount = jsonNode.get("total_count").asInt();

                // ID existe en la API
                return totalCount > 0;
            } else {
                // ID no existe en la API
                return false;
            }
        } catch (IOException | InterruptedException e) {
            // Loguear cualquier excepción para propósitos de depuración
            Logger.getLogger(getClass().getName())
                    .severe("Error al verificar la existencia del ID en la API: " + e.getMessage());
            return false;
        }
    }

}
