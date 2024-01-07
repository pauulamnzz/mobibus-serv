package net.ausiasmarch.mobibus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.ausiasmarch.mobibus.repository.ParadaFavRepository;

@Service
public class ParadaFavService {
   /* 
 @Autowired
    private RestTemplate restTemplate;
 
    public ParadaApiDto obtenerDatosEnTiempoReal(String idParadaApi) {
        String apiUrl = "https://valencia.opendatasoft.com/api/explore/v2.1/catalog/datasets/emt/records?limit=25&id_parada_api=" + idParadaApi;

        ResponseEntity<ParadaApiDto> response = restTemplate.getForEntity(apiUrl, ParadaApiDto.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            // Manejar error en la llamada a la API según tus necesidades
            return null;
        }
    }
    */
}

