package net.ausiasmarch.mobibus.api;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.mobibus.entity.UserEntity;
import net.ausiasmarch.mobibus.entity.ParadaFavEntity;
import net.ausiasmarch.mobibus.service.ParadaFavService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/parada_fav")
public class ParadaFavApi {

    @Autowired
    ParadaFavService oParadaFavService;

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody ParadaFavEntity oParadaFavEntity) {
        return ResponseEntity.ok(oParadaFavService.create(oParadaFavEntity));
    }



    @PutMapping("")
    public ResponseEntity<ParadaFavEntity> update(@RequestBody ParadaFavEntity oParadaFavEntity) {
        return ResponseEntity.ok(oParadaFavService.update(oParadaFavEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParadaFavEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oParadaFavService.get(id));
    }


    @GetMapping("")
    public ResponseEntity<Page<ParadaFavEntity>> getPage(Pageable oPageable,
            @RequestParam(value = "user", defaultValue = "0", required = false) Long userId) {
        return ResponseEntity.ok(oParadaFavService.getPage(oPageable, userId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oParadaFavService.delete(id));
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oParadaFavService.empty());
    }

    @GetMapping("/fav/{userId}")
    public ResponseEntity<List<ParadaFavEntity>> getParadasFavoritasByUserId(@PathVariable Long userId) {
        List<ParadaFavEntity> paradasFavoritas = oParadaFavService.getParadasFavoritasByUserId(userId);
        return ResponseEntity.ok(paradasFavoritas);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkParadaFavExistsForUser(
            @RequestParam Long idParada,
            @RequestParam Long userId) {
        boolean exists = oParadaFavService.paradaFavExistsForUser(idParada, userId);
        return ResponseEntity.ok(exists);
    }

}
