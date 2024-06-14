package net.ausiasmarch.mobibus.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.mobibus.bean.UserBean;
import net.ausiasmarch.mobibus.service.SessionService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/initial/session")
//@RequestMapping("/session")

public class SessionController {
     @Autowired
    SessionService oSessionService;
  // @PostMapping()
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserBean oUserBean) {
        return ResponseEntity.ok("\"" + oSessionService.login(oUserBean) + "\"");
    }
}
