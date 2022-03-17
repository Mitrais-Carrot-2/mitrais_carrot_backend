package com.team.two.mitrais_carrot.controller.auth;

import javax.validation.Valid;

import com.team.two.mitrais_carrot.dto.auth.JwtDto;
import com.team.two.mitrais_carrot.dto.auth.LoginDto;
import com.team.two.mitrais_carrot.dto.auth.SignUpDto;
import com.team.two.mitrais_carrot.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

//    @GetMapping("/test")
//    public UserDetailsImpl test() {
//        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return user;
//    }

    @PostMapping("/login")
    public JwtDto authenticateUser(@Valid @RequestBody LoginDto loginRequest) {
        return authService.login(loginRequest);
    }
}
