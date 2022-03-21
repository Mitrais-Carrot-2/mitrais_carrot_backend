package com.team.two.mitrais_carrot.controller.award;

import com.team.two.mitrais_carrot.dto.award.CreateAwardDTO;
import com.team.two.mitrais_carrot.entity.award.AwardEntity;
import com.team.two.mitrais_carrot.service.award.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/award")
public class AwardController {
    @Autowired
    AwardService awardService;

    @GetMapping("")
    public List<AwardEntity> getAllAward(){
        return awardService.getAllAwards();
    }

    @PostMapping("")
    public ResponseEntity<?> createAward(@RequestBody CreateAwardDTO request){
        return  awardService.createAward(request);
    }
}
