package net.ausiasmarch.mobibus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.mobibus.entity.ParadaFavEntity;


public interface ParadaFavRepository extends JpaRepository<ParadaFavEntity, Long> {

        Optional<ParadaFavEntity> findByDenominacion(String denominacion);

}
