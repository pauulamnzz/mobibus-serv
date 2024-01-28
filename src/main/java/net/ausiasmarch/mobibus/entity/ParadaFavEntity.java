package net.ausiasmarch.mobibus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "parada_fav")
public class ParadaFavEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alias;

    private Long id_parada;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    

    public ParadaFavEntity() {
    }

    public ParadaFavEntity(Long id) {
        this.id = id;
    }

    public ParadaFavEntity(UserEntity user, String alias) {
        this.user = user;
        this.alias=alias;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getId_parada() {
        return id_parada;
    }

    public void setId_parada(Long id_parada) {
        this.id_parada = id_parada;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }




}