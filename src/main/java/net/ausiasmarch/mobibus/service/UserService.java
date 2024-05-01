package net.ausiasmarch.mobibus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import net.ausiasmarch.mobibus.entity.ParadaFavEntity;
import net.ausiasmarch.mobibus.entity.UserEntity;
import net.ausiasmarch.mobibus.exception.ResourceNotFoundException;
import net.ausiasmarch.mobibus.repository.ParadaFavRepository;
import net.ausiasmarch.mobibus.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UserService {
    private final String mobibusPASSWORD = "27a104fe5c6970d18c073f686cca72ec9c4b1ec187b7537ed6144fe76baac8c1";

    @Autowired
    UserRepository oUserRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    @Autowired
    ParadaFavRepository oParadaFavRepository;

    public UserEntity get(Long id) {
        return oUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuari no trobat"));
    }

    public Page<UserEntity> getPage(Pageable oPageable) {
        oSessionService.onlyAdmins();
        return oUserRepository.findAll(oPageable);
    }

    public Long create(UserEntity oUserEntity) {
      oSessionService.onlyAdmins(); 
      if(oUserRepository.findByUsername(oUserEntity.getUsername()).isPresent()){
          throw new ResourceNotFoundException("Usuari ja existent");
      }else{
        oUserEntity.setId(null);
        oUserEntity.setPassword(mobibusPASSWORD);
        return oUserRepository.save(oUserEntity).getId();
      }
    }

    public UserEntity getByUsername(String username) {
        return oUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuari no trobat per username"));
    }

    public Long delete(Long id) {
        oSessionService.onlyAdmins();
        oUserRepository.deleteById(id);
        return id;
    }

    public UserEntity update(UserEntity oUserEntityToSet) {
        UserEntity oUserEntityFromDatabase = this.get(oUserEntityToSet.getId());
        oSessionService.onlyAdminsOrUsersWithIisOwnData(oUserEntityFromDatabase.getId());
        if (oSessionService.isUser()) {
            oUserEntityToSet.setRole(oUserEntityFromDatabase.getRole());
            oUserEntityToSet.setPassword(mobibusPASSWORD);
            return oUserRepository.save(oUserEntityToSet);
        } else {
            oUserEntityToSet.setPassword(mobibusPASSWORD);
            return oUserRepository.save(oUserEntityToSet);
        }
    }

    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        List<UserEntity> usuarios = oUserRepository.findAll();
        // Iterar sobre cada usuario
        for (UserEntity usuario : usuarios) {
            // Obtener todas las paradas favoritas asociadas con el usuario actual
            List<ParadaFavEntity> paradasFavs = oParadaFavRepository.findByUser(usuario);
            // Verificar si el usuario no tiene paradas favoritas asociadas
            if (paradasFavs.isEmpty()) {
                // Si el usuario no tiene paradas favoritas asociadas, eliminar el usuario
                oUserRepository.delete(usuario);
            }
        }

        oUserRepository.resetAutoIncrement();
        // UserEntity oJugadorEntity1 = new UserEntity("Paula", mobibusPASSWORD, "asdd@gmail.com", false);
        // oUserRepository.save(oJugadorEntity1);
        // oJugadorEntity1 = new UserEntity("Hugo", mobibusPASSWORD, "taric@gmail.com", true);
        // oUserRepository.save(oJugadorEntity1);
        return oUserRepository.count();
    }
}
