package net.ausiasmarch.mobibus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_parada_fav")
public class UserParadaFavEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_parada_fav")
    private ParadaFavEntity parada_fav;

    public UserParadaFavEntity() {
    }

    public UserParadaFavEntity(Long id) {
        this.id = id;
    }

    public UserParadaFavEntity(UserEntity user, ParadaFavEntity parada_fav) {
        this.user = user;
        this.parada_fav = parada_fav;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ParadaFavEntity getParada_fav() {
        return parada_fav;
    }

    public void setParada_fav(ParadaFavEntity parada_fav) {
        this.parada_fav = parada_fav;
    }


    
}
