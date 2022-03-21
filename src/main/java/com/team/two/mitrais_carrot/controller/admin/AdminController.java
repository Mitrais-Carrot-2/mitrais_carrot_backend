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

    @PutMapping("barnReward")
    public BarnRewardEntity editBarnReward(@RequestBody EditBarnRewardDto request) {
        return barnRewardService.editBarnRewardEntity(request);
    }
}
