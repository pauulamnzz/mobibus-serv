package net.ausiasmarch.mobibus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.ausiasmarch.mobibus.entity.ParadaFavEntity;
import net.ausiasmarch.mobibus.entity.UserEntity;
import net.ausiasmarch.mobibus.exception.ResourceNotFoundException;
import net.ausiasmarch.mobibus.repository.UserRepository;

@Service
public class UserService {
    private final String foxforumPASSWORD = "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e";


    @Autowired
    UserRepository oUserRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;


        public Long create(UserEntity oUserEntity) {
       //oSessionService.onlyAdmins();
        oUserEntity.setId(null);
        oUserEntity.setPassword(foxforumPASSWORD);
        return oUserRepository.save(oUserEntity).getId();
    }
        public UserEntity get(Long id) {
        return oUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    public UserEntity getByUsername(String username) {
        return oUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by username"));
    }
    public Long delete(Long id) {
        UserEntity oUserEntity = oUserRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User no encontrado con ID " + id));
    // oSessionService.onlyAdmins();
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
}
