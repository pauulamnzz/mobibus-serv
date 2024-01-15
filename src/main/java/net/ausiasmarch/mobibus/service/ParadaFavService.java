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

@Service
public class ParadaFavService {

    private static final String API_URL = "https://valencia.opendatasoft.com/api/explore/v2.1/catalog/datasets/emt/records";

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    @Autowired
    ParadaFavRepository oParadaFavRepository;

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

    

public ParadaFavEntity update(ParadaFavEntity oParadaFavEntityToSet) {
   // ParadaFavEntity oParadaFavEntityFromDatabase = this.get(oParadaFavEntityToSet.getId());
    return oParadaFavRepository.save(oParadaFavEntityToSet);


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
