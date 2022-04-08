package com.team.two.mitrais_carrot.controller.user;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.UpdatePasswordDto;
import com.team.two.mitrais_carrot.dto.UpdateProfileDto;
import com.team.two.mitrais_carrot.dto.auth.UserDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("")
    public UserEntity addUser(@RequestBody UserDto userDto) {
        return userService.add(userDto);
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserEntity> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public UserEntity getUser(@PathVariable("id") String id) {
        return userService.getById(Long.parseLong(id));
    }

    @GetMapping("username/{username}")
    public ResponseEntity<?> getByName(@PathVariable("username") String username) {
        if (userService.getByUsername(username) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getByUsername(username));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageDto("Username not found or Password is wrong!", false));
    }

    @PutMapping("updatePassword/{username}")
    public ResponseEntity<?> updatePassword(@PathVariable("username") String username,
            @RequestBody UpdatePasswordDto updatePasswordDto) {
        if (userService.checkPassword(username, updatePasswordDto.getOldPassword())) {
            userService.changePassword(username, updatePasswordDto.getNewPassword());
            // response entity return ok and message password has been changed!
            return ResponseEntity.status(HttpStatus.OK).body("Password has been changed!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is wrong!");
        }
    }

    @PutMapping("updateProfile/{username}")
    public ResponseEntity<?> updateProfile(@PathVariable("username") String username,
            @RequestBody UpdateProfileDto updateProfileDto) {
        if (userService.checkPassword(username, updateProfileDto.getCurrentPassword())) {
            userService.updateProfile(username, updateProfileDto);
            return ResponseEntity.status(HttpStatus.OK).body("Profile has been changed!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is wrong!");
        }
    }

    @GetMapping("/Image/{username}")
    public ResponseEntity<byte[]> getImage(@PathVariable String username) throws Exception {
        UserEntity user = userService.getByUsername(username);
        if (user != null) {
            if (user.getImage() != null) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"Profile " + user.getUsername() + " Image \"")
                        .contentType(MediaType.valueOf(user.getImageType()))
                        .body(user.getImage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/Image/{username}")
    public ResponseEntity<?> uploadImage(@PathVariable String username, @RequestParam("file") MultipartFile file) {
        try {
            userService.saveImage(username, file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }
}
