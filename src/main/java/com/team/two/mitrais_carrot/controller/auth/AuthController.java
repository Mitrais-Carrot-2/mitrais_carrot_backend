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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public JwtDto authenticateUser(@Valid @RequestBody LoginDto loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtDto(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
//        return ResponseEntity.ok(roles);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpRequest) {
         if (userRepository.existsByUsername(signUpRequest.getUsername())) {
             return ResponseEntity
                     .badRequest()
                     .body(new MessageDto("Error: Username is already taken!"));
         }

         if (userRepository.existsByEmail(signUpRequest.getEmail())) {
             return ResponseEntity
                     .badRequest()
                     .body(new MessageDto("Error: Email is already in use!"));
         }

         // Create new user's account
         UserEntity user = new UserEntity(signUpRequest.getUsername(),
                 encoder.encode(signUpRequest.getPassword()),
                 signUpRequest.getEmail());

         Set<String> strRoles = signUpRequest.getRole();
         Set<RoleEntity> roles = new HashSet<>();

//         RoleEntity userRole = roleRepository.findByName(ERole.STAFF)
//                 .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//         roles.add(userRole);

             strRoles.forEach(role -> {
                 switch (role.toLowerCase()) {
                     case "admin":
                         RoleEntity adminRole = roleRepository.findByName(ERole.ADMIN)
                                 .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                         roles.add(adminRole);

                         break;
                     case "farmer":
                         RoleEntity farmerRole = roleRepository.findByName(ERole.FARMER)
                                 .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                         roles.add(farmerRole);
                         break;
                     case "staff":
                         RoleEntity staffRole = roleRepository.findByName(ERole.STAFF)
                                 .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                         roles.add(staffRole);
                         break;
                     case "manager":
                         RoleEntity managerRole = roleRepository.findByName(ERole.MANAGER)
                                 .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                         roles.add(managerRole);
                         break;
                     case "merchant":
                         RoleEntity merchantRole = roleRepository.findByName(ERole.MERCHANT)
                                 .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                         roles.add(merchantRole);
                         break;
                 }
             });

         if (roles.isEmpty()){
             RoleEntity staffRole = roleRepository.findByName(ERole.STAFF)
                     .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
             roles.add(staffRole);
         }
         user.setRoles(roles);
         userRepository.save(user);

         List<UserRoleEntity> userRoleEntity = new ArrayList<>();
         roles.forEach(r -> {
             userRoleEntity.add( new UserRoleEntity(user.getId(), r.getId()));
         });

         userRoleRepository.saveAll(userRoleEntity);

        return ResponseEntity.ok("Sign Up Successfully!");
    }
}
