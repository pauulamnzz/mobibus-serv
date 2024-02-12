package net.ausiasmarch.mobibus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ausiasmarch.mobibus.entity.ParadaFavEntity;

public interface ParadaFavRepository extends JpaRepository<ParadaFavEntity, Long> {

    @Modifying
    @Query(value = "ALTER TABLE user_parada_fav AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    Page<ParadaFavEntity> findByUserId(Long id, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(pf) > 0 THEN true ELSE false END FROM ParadaFavEntity pf WHERE pf.user.id = :userId AND pf.id_parada = :id_parada")
    boolean existsByUserIdAndId_parada(@Param("userId") Long userId, @Param("id_parada") Long id_parada);

    

}
