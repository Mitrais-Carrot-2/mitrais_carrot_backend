package com.team.two.mitrais_carrot.controller.admin;

import com.team.two.mitrais_carrot.dto.admin.BarnRewardDto;
import com.team.two.mitrais_carrot.dto.admin.EditBarnRewardDto;
import com.team.two.mitrais_carrot.dto.auth.SignUpDto;
import com.team.two.mitrais_carrot.entity.admin.BarnRewardEntity;
import com.team.two.mitrais_carrot.service.admin.BarnRewardService;
import com.team.two.mitrais_carrot.service.auth.AuthService;
import com.team.two.mitrais_carrot.service.transfer.TransferService;
import com.team.two.mitrais_carrot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    @Autowired
    BarnRewardService barnRewardService;

    @Autowired
    UserService userService;

    @Autowired
    TransferService transferService;

    @Autowired
    AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpRequest) {
        return authService.register(signUpRequest);
    }

    @GetMapping("barnReward")
    public List<BarnRewardEntity> fetchBarnReward() {
        return barnRewardService.fetchAllBarnRewards();
    }

    @PostMapping("barnReward")
    public ResponseEntity<?> postBarnReward(@RequestBody BarnRewardDto request) {
        return barnRewardService.createBarnReward(request);
    }

    @PutMapping("barnReward/{id}")
    public BarnRewardEntity editBarnReward(@PathVariable int id,
            @RequestBody EditBarnRewardDto request) {
        return barnRewardService.editBarnRewardEntity(id, request);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("barnReward/{id}")
    public List<BarnRewardEntity> fetchBarnRewardById(@PathVariable int id) {
        return barnRewardService.fetchBarnRewardById(id);
    }

    @DeleteMapping("/barnReward/{id}")
    public ResponseEntity<?> deleteBarnReward(@PathVariable int id) {
        return barnRewardService.deleteBarnReward(id);
    }
}
