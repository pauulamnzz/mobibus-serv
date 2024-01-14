package net.ausiasmarch.mobibus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import jakarta.servlet.http.HttpServletRequest;
import net.ausiasmarch.mobibus.entity.ParadaFavEntity;
import net.ausiasmarch.mobibus.repository.ParadaFavRepository;


@Service
public class ParadaFavService {

     @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    @Autowired
    ParadaFavRepository oParadaFavRepository;

    
     // Operación CREATE
    public ParadaFavEntity createParadaFav(ParadaFavEntity nuevaParadaFav) {
        // Puedes realizar validaciones adicionales si es necesario
        return oParadaFavRepository.save(nuevaParadaFav);
    }
}

