package net.ausiasmarch.mobibus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.mobibus.entity.UserParadaFavEntity;

public interface UserParadaFavRepository extends JpaRepository<UserParadaFavEntity, Long> {
    
    @Modifying
    @Query(value = "ALTER TABLE user_parada_fav AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    Page<UserParadaFavEntity> findByUserId(Long id, Pageable pageable);

}
