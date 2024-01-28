package net.ausiasmarch.mobibus.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.IOException;
import jakarta.transaction.Transactional;
import net.ausiasmarch.mobibus.entity.UserEntity;
import net.ausiasmarch.mobibus.entity.ParadaFavEntity;
import net.ausiasmarch.mobibus.exception.ResourceNotFoundException;
import net.ausiasmarch.mobibus.repository.ParadaFavRepository;

@Service
public class ParadaFavService {
    private static final String API_URL = "https://valencia.opendatasoft.com/api/explore/v2.1/catalog/datasets/emt/records";
    
    @Autowired
    ParadaFavRepository oUserParadaFavRepository;  

@Autowired
SessionService oSessionService;

public ParadaFavEntity get(Long id) {
       // oSessionService.onlyAdmins();
        return oUserParadaFavRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Thread not found"));
   
}
   public Page<ParadaFavEntity> getPage(Pageable oPageable) {
        return oUserParadaFavRepository.findAll(oPageable);
    } 
    public Long create(ParadaFavEntity nuevaParadaFav) {
        Long id_parada = nuevaParadaFav.getId_parada();
        try {
            if(checkIdExists(id_parada)){
                oUserParadaFavRepository.save(nuevaParadaFav);
                return nuevaParadaFav.getId();
            }else{
                throw new RuntimeException("El ID de parada no existe. No se puede crear la nueva parada favorita.");            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
       // oSessionService.onlyAdminsOrUsers();
       nuevaParadaFav.setId(null);
        return oUserParadaFavRepository.save(nuevaParadaFav).getId();
        
    }

    public ParadaFavEntity update(ParadaFavEntity oUserParadaFavEntityToSet) {
        ParadaFavEntity oUserParadaFavEntityFromDatabase = this.get(oUserParadaFavEntityToSet.getId());
        oSessionService.onlyAdminsOrUsersWithIisOwnData(oUserParadaFavEntityFromDatabase.getUser().getId());
        if (oSessionService.isUser()) {
            if (oUserParadaFavEntityToSet.getUser().getId().equals(oSessionService.getSessionUser().getId())) {
                return oUserParadaFavRepository.save(oUserParadaFavEntityToSet);
            } else {
                throw new ResourceNotFoundException("Unauthorized");
            }
        } else {
            return oUserParadaFavRepository.save(oUserParadaFavEntityToSet);
        }
    }

    public Long delete(Long id) {
        ParadaFavEntity oUserParadaFavEntityFromDatabase = this.get(id);
        oSessionService.onlyAdminsOrUsersWithIisOwnData(oUserParadaFavEntityFromDatabase.getUser().getId());
        oUserParadaFavRepository.deleteById(id);
        return id;
    }
    @Transactional
    public Long empty() {
       // oSessionService.onlyAdmins();
        oUserParadaFavRepository.deleteAll();
        oUserParadaFavRepository.resetAutoIncrement();
        oUserParadaFavRepository.flush();
        return oUserParadaFavRepository.count();
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
