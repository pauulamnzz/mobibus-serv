package net.ausiasmarch.mobibus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.mobibus.entity.ParadaFavEntity;
import net.ausiasmarch.mobibus.service.ParadaFavService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/parada_fav")
public class ParadaFavApi {
    

    @Autowired
    ParadaFavService oParadaFavService;

     // Crear una nueva parada favorita
    @PostMapping
    public ResponseEntity<ParadaFavEntity> createParadaFav(@RequestBody ParadaFavEntity nuevaParadaFav) {
        ParadaFavEntity paradaFavCreada = oParadaFavService.createParadaFav(nuevaParadaFav);
        return ResponseEntity.ok(paradaFavCreada);
    }
}
