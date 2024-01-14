package net.ausiasmarch.mobibus.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import net.ausiasmarch.mobibus.entity.ParadaFavEntity;
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
    /*// Operación CREATE
      public ParadaFavEntity createParadaFav(ParadaFavEntity nuevaParadaFav) {
        return oParadaFavRepository.save(nuevaParadaFav);
    } */
      
    
  // Operación CREATE
  public ResponseEntity<?> createParadaFav(ParadaFavEntity nuevaParadaFav) throws java.io.IOException {
    Long idParada = nuevaParadaFav.getId_parada_api();

    // Verificar si el ID de la parada existe en la API antes de guardar en la base de datos
    if (checkIdExists(idParada)) {
        oParadaFavRepository.save(nuevaParadaFav);
        return ResponseEntity.ok().build(); // Puedes ajustar según tus necesidades
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

/**
 * Verifica si un identificador dado existe en la API mediante una solicitud HTTP.
 *
 * @param id El identificador a verificar en la API.
 * @return true si el identificador existe en la API, false en caso contrario.
 * @throws java.io.IOException 
 */
public boolean checkIdExists(Long id) throws java.io.IOException {
    try {
        // Construir la URL de la API utilizando el identificador proporcionado
        String apiUrl = API_URL + "?where=id_parada=" + id + "&limit=1";

        // Loguear la URL de la API para propósitos de depuración
        Logger.getLogger(getClass().getName()).info("API URL: " + apiUrl);

        // Crear una instancia de HttpClient para realizar la solicitud HTTP
        HttpClient httpClient = HttpClient.newHttpClient();

        // Construir la solicitud HTTP GET
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        // Enviar la solicitud HTTP y obtener la respuesta
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Loguear la respuesta de la API para propósitos de depuración
        Logger.getLogger(getClass().getName()).info("API Response: " + httpResponse.body());

        // Verificar si la respuesta es exitosa (código 2xx) y si contiene datos
        if (httpResponse.statusCode() == HttpStatus.OK.value()) {
            // Convertir la respuesta JSON a un objeto para analizar el valor de total_count
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(httpResponse.body());

            // Obtener el valor de total_count desde la respuesta JSON
            int totalCount = jsonNode.get("total_count").asInt();

            // Verificar si total_count es mayor que cero (indicando que el recurso existe)
            return totalCount > 0;
        } else {
            // Si la respuesta no es exitosa, consideramos que el recurso no existe
            return false;
        }
    } catch (IOException | InterruptedException e) {
        // Loguear cualquier excepción para propósitos de depuración
        Logger.getLogger(getClass().getName()).severe("Error al verificar la existencia del ID en la API: " + e.getMessage());
        return false;
    }
}


}

