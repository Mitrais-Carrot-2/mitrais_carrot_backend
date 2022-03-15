package com.team.two.mitrais_carrot.controller.farmer;

import com.team.two.mitrais_carrot.dto.farmer.BarnToFreezerDto;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import com.team.two.mitrais_carrot.service.farmer.BarnToFreezerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@PreAuthorize("hasAnyRole('FARMER')")
@RequestMapping("/api/farmer/transfer")
public class BarnToFreezerController {
    @Autowired
    BarnToFreezerService barnToFreezerService;

    @PostMapping("")
    public FreezerEntity transfer(@RequestBody BarnToFreezerDto transferDto){
        return barnToFreezerService.sendToManager(transferDto);
    }
}
