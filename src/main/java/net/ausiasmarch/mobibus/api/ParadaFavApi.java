package net.ausiasmarch.mobibus.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import net.ausiasmarch.mobibus.entity.ParadaFavEntity;
import net.ausiasmarch.mobibus.entity.UserEntity;
import net.ausiasmarch.mobibus.service.ParadaFavService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/parada_fav")
public class ParadaFavApi {
    

    @Autowired
    ParadaFavService oParadaFavService;

@PostMapping
public ResponseEntity<?> create(@RequestBody ParadaFavEntity nuevaParadaFav) throws IOException {
    return oParadaFavService.create(nuevaParadaFav);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    oParadaFavService.delete(id);
    return ResponseEntity.ok().build();
}

@PutMapping
public ResponseEntity<ParadaFavEntity> update(@RequestBody ParadaFavEntity oParadaFavEntity)  {
    return ResponseEntity.ok(oParadaFavService.update(oParadaFavEntity));
}

@GetMapping("/{id}")
public ResponseEntity<ParadaFavEntity> get(@PathVariable("id") Long id) {
    return ResponseEntity.ok(oParadaFavService.get(id));
}

    @GetMapping("")
    public ResponseEntity<Page<ParadaFavEntity>> getPage(Pageable oPageable) {
        return ResponseEntity.ok(oParadaFavService.getPage(oPageable));
    }

}