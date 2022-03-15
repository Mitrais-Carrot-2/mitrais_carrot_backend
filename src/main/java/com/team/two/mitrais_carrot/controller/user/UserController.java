package com.team.two.mitrais_carrot.controller.user;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.UpdatePasswordDto;
import com.team.two.mitrais_carrot.dto.UpdateProfileDto;
import com.team.two.mitrais_carrot.dto.auth.UserDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.image.FileNameHelper;
import com.team.two.mitrais_carrot.entity.image.Image;
import com.team.two.mitrais_carrot.entity.image.ImageResponse;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.service.basket.EBasket;
import com.team.two.mitrais_carrot.service.Image.ImageService;
import com.team.two.mitrais_carrot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
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
    public UserEntity addUser(@RequestBody UserDto userDto){
        return userService.add(userDto);
    }
    @Autowired
    private ImageService imageService;

    private FileNameHelper fileHelper = new FileNameHelper();

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/")
    public List<UserEntity> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("{id}")
    public UserEntity getUser(@PathVariable("id") String id){
        return userService.getById(Long.parseLong(id));
    }

    @GetMapping("username/{username}")
    public ResponseEntity<?> getByName(@PathVariable("username") String username){
        if(userService.getByUsername(username)!=null){
            return ResponseEntity.status(HttpStatus.OK).body(userService.getByUsername(username));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto("Username not found or Password is wrong!", false));
    }

    @PutMapping("updatePassword/{username}")
    public ResponseEntity<?> updatePassword(@PathVariable("username") String username, @RequestBody UpdatePasswordDto updatePasswordDto){
        if(userService.checkPassword(username, updatePasswordDto.getOldPassword())){
            userService.changePassword(username, updatePasswordDto.getNewPassword());
            //response entity return ok and message password has been changed!
            return ResponseEntity.status(HttpStatus.OK).body("Password has been changed!");
        }else{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is wrong!");
        }
    }

    @PutMapping("updateProfile/{username}")
    public ResponseEntity<?> updateProfile(@PathVariable("username") String username, @RequestBody UpdateProfileDto updateProfileDto){
        if(userService.checkPassword(username, updateProfileDto.getCurrentPassword())){
            if(userService.getByUsername(updateProfileDto.getUsername())!=null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exist!");
            }
            userService.updateProfile(username, updateProfileDto);
            //response entity return ok and message password has been changed!
            return ResponseEntity.status(HttpStatus.OK).body("Profile has been changed!");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is wrong!");
        }
    }

//    public Image getImageByName(String name) throws Exception {
//        Image image = imageService.findByFileName(name);
//        if (image == null) {
//            return Image.defaultImage();
//        }
//        return image;
//    }

    @GetMapping("/getImage/{username}")
    public ResponseEntity<byte[]> getImage(@PathVariable String username) throws Exception {
        UserEntity user = userService.getByUsername(username);
        if (user != null) {
            if(user.getImage()!=null){
                return ResponseEntity.ok().contentType(MediaType.valueOf(user.getImageType())).body(user.getImage());
            }
//            UserEntity userEntity = userService.buildImage(username, "/Users/ilaminaty/Desktop/repo/mitrais_carrot_backend/src/images/userdefaultImage.png");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/uploadImage/{username}")
    public ResponseEntity<?> uploadImage(@PathVariable String username,@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }
        UserEntity user = userService.getByUsername(username);
        if(user != null){
            UserEntity userImage = userService.buildImage(username , file);
            return ResponseEntity.ok().contentType(MediaType.valueOf(user.getImageType())).body(user.getImage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please import image!");
    }

    //postmapping change user profile
//    @PutMapping("{username}/updateProfile")
//    public MessageDto updateProfile(@PathVariable("username") String username, @RequestBody String password ,@RequestBody UpdateProfileDto updateProfileDto){
//        userService.getByUsername(username);
//        if(userEntity != null){
//            if(userEntity.getPassword().equals(encoder.encode(password))){
//                userService.updateProfile(userEntity.getId(),updateProfileDto);
//                return new MessageDto("Profile Updated!");
//            }
//        }
//            return new MessageDto("Wrong Password!");
//        }
