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
    private final String foxforumPASSWORD = "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e";


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
        oUserEntity.setId(null);
        oUserEntity.setPassword(foxforumPASSWORD);
        return oUserRepository.save(oUserEntity).getId();
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
            oUserEntityToSet.setPassword(foxforumPASSWORD);
            return oUserRepository.save(oUserEntityToSet);
        } else {            
            oUserEntityToSet.setPassword(foxforumPASSWORD);
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
    return oUserRepository.count();
}
}
