package com.team.two.mitrais_carrot.controller.farmer;

import com.team.two.mitrais_carrot.dto.farmer.BarnDto;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.service.farmer.BarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
// @PreAuthorize("hasAnyRole('FARMER')")
@RequestMapping("/api/farmer/barn")
public class BarnController {
    @Autowired
    BarnService barnService;

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public List<BarnEntity> fetchBarn() {
        return barnService.fetchAllBarn();
    }

    @PostMapping("/")
    public ResponseEntity<?> createBarn(@RequestBody BarnDto barnDto) {
        return barnService.createBarn(barnDto);
    }
}
