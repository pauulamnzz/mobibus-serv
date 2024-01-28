package net.ausiasmarch.mobibus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import net.ausiasmarch.mobibus.entity.UserEntity;
import net.ausiasmarch.mobibus.entity.ParadaFavEntity;
import net.ausiasmarch.mobibus.service.ParadaFavService;
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/parada_fav")
public class ParadaFavApi {
    
@Autowired
ParadaFavService oUserParadaFavService;

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody ParadaFavEntity oUserParadaFavEntity) {
    return ResponseEntity.ok(oUserParadaFavService.create(oUserParadaFavEntity));
    }
     @PutMapping("")
    public ResponseEntity<ParadaFavEntity> update(@RequestBody ParadaFavEntity oUserParadaFavEntity) {
        return ResponseEntity.ok(oUserParadaFavService.update(oUserParadaFavEntity));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ParadaFavEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oUserParadaFavService.get(id));
    }
       @GetMapping("")
    public ResponseEntity<Page<ParadaFavEntity>> getPage(Pageable oPageable) {
        return ResponseEntity.ok(oUserParadaFavService.getPage(oPageable));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oUserParadaFavService.delete(id));
    }
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oUserParadaFavService.empty());
    }
}
