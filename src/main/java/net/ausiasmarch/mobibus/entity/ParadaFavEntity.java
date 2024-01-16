package net.ausiasmarch.mobibus.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "parada_fav")
public class ParadaFavEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String denominacion;

   private Long id_parada_api;
 

@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE )
@JoinTable(
    name="user_parada_fav", joinColumns = @JoinColumn(name="id_parada_fav", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="id_user", referencedColumnName = "id")
)
private List<UserEntity> users;



    public ParadaFavEntity() {
}

    public ParadaFavEntity(Long id, String denominacion, Long id_parada_api) {
        this.id = id;
        this.denominacion = denominacion;
        this.id_parada_api = id_parada_api;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public Long getId_parada_api() {
        return id_parada_api;
    }

    public void setId_parada_api(Long id_parada_api) {
        this.id_parada_api = id_parada_api;
    }

}
