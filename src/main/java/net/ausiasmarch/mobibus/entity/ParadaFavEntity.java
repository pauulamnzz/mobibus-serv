package net.ausiasmarch.mobibus.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "parada_fav")
public class ParadaFavEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long linea;

    private String denominacion;
    

    @OneToMany(mappedBy = "parada_fav", fetch = jakarta.persistence.FetchType.LAZY)
    private List<UserParadaFavEntity> users_paradas_favs;

   public ParadaFavEntity() {
        users_paradas_favs = new ArrayList<>();
    }

public ParadaFavEntity(Long id, Long linea, String denominacion) {
    this.id = id;
    this.linea = linea;
    this.denominacion = denominacion;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public Long getLinea() {
    return linea;
}

public void setLinea(Long linea) {
    this.linea = linea;
}

public String getDenominacion() {
    return denominacion;
}

public void setDenominacion(String denominacion) {
    this.denominacion = denominacion;
}

public int getUsersParadasFavs() {
    return users_paradas_favs.size();
}



}
