package com.team.two.mitrais_carrot.controller.farmer;

import com.team.two.mitrais_carrot.dto.farmer.TransferToManagerDto;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import com.team.two.mitrais_carrot.service.farmer.TransferToManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@PreAuthorize("hasAnyRole('FARMER')")
@RequestMapping("/api/farmer/transfer")
public class TransferToManagerController {
    @Autowired
    TransferToManagerService transferToManagerService;

    @PostMapping("")
    public FreezerEntity transfer(TransferToManagerDto transferDto){
        return transferToManagerService.sendToManager(transferDto);
    }
}
