package net.ausiasmarch.mobibus.entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name = "user")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password = "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e";

    private String email;

    private Boolean role = false;

 
@JsonIgnore
@ManyToMany(mappedBy = "users")
private List<ParadaFavEntity> paradas_favs;




    public UserEntity() {
}

    public UserEntity(Long id, String username, String password, String email, Boolean role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity(String username, String password, String email, Boolean role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public List<ParadaFavEntity> getParadas_favs() {
        return paradas_favs;
    }

    public void setParadas_favs(List<ParadaFavEntity> paradas_favs) {
        this.paradas_favs = paradas_favs;
    }

    public Optional<UserEntity> findById(Long id2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

   



}
