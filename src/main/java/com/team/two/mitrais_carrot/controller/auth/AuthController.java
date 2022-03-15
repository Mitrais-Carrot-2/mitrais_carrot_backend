package com.team.two.mitrais_carrot.controller.auth;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.auth.JwtDto;
import com.team.two.mitrais_carrot.dto.auth.LoginDto;
import com.team.two.mitrais_carrot.dto.auth.SignUpDto;
import com.team.two.mitrais_carrot.entity.auth.ERole;
import com.team.two.mitrais_carrot.entity.auth.RoleEntity;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.auth.UserRoleEntity;
import com.team.two.mitrais_carrot.repository.RoleRepository;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.repository.UserRoleRepository;
import com.team.two.mitrais_carrot.security.jwt.JwtUtils;
import com.team.two.mitrais_carrot.security.services.UserDetailsImpl;
import com.team.two.mitrais_carrot.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @GetMapping("/test")
    public UserDetailsImpl test() {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

    @PostMapping("/login")
    public JwtDto authenticateUser(@Valid @RequestBody LoginDto loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpRequest) {
        return authService.register(signUpRequest);
    }
}
