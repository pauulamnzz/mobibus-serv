package net.ausiasmarch.mobibus.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import net.ausiasmarch.mobibus.entity.ParadaFavEntity;
import net.ausiasmarch.mobibus.service.ParadaFavService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/parada_fav")
public class ParadaFavApi {
    

    @Autowired
    ParadaFavService oParadaFavService;

    /*    // Crear una nueva parada favorita
    @PostMapping
    public ResponseEntity<ParadaFavEntity> createParadaFav(@RequestBody ParadaFavEntity nuevaParadaFav) {
        ParadaFavEntity paradaFavCreada = oParadaFavService.createParadaFav(nuevaParadaFav);
        return ResponseEntity.ok(paradaFavCreada);
    }
 */
// Endpoint para crear una nueva parada favorita sin validación explícita
@PostMapping
public ResponseEntity<?> createParadaFav(@RequestBody ParadaFavEntity nuevaParadaFav) throws IOException {
    return oParadaFavService.createParadaFav(nuevaParadaFav);
}

    
/*  @GetMapping("/check/{id}")
    public ResponseEntity<String> checkId(@PathVariable int id) throws JsonMappingException, JsonProcessingException {
        if (oParadaFavService.checkIdExists(id)) {
            return new ResponseEntity<>("El ID " + id + " existe en la API.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("El ID " + id + " no existe en la API.", HttpStatus.NOT_FOUND);
        }
    } */
   
}
