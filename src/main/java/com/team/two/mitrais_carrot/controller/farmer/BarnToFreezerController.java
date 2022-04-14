package com.team.two.mitrais_carrot.controller.farmer;

import java.util.List;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.farmer.BarnToFreezerDto;
import com.team.two.mitrais_carrot.dto.farmer.DistributeDto;
import com.team.two.mitrais_carrot.dto.user.StaffDto;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;
import com.team.two.mitrais_carrot.service.farmer.BarnToFreezerService;
import com.team.two.mitrais_carrot.service.transfer.TransferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
// @PreAuthorize("hasAnyRole('FARMER')")
@RequestMapping("/api/farmer/transfer")
public class BarnToFreezerController {
    @Autowired
    BarnToFreezerService barnToFreezerService;
    @Autowired
    TransferService transferService;

    @PreAuthorize("hasAnyRole('FARMER')")
    @PostMapping("")
    public ResponseEntity<?> transfer(@RequestBody BarnToFreezerDto transferDto) {
        final Boolean status = barnToFreezerService.sendToManager(transferDto);
        if (status) {
            return ResponseEntity.ok(new MessageDto("Transfer from Barn to Freezer success!", true));
        } else {
            return ResponseEntity.badRequest().body(new MessageDto("Not enough carrot in Barn!", false));
        }
    }

    @PreAuthorize("hasAnyRole('FARMER')")
    @PostMapping("/distribute")
    public TransferEntity transfer(@RequestBody DistributeDto req) {
        return transferService.transferBarnToFreezer(req);
    }

    @PreAuthorize("hasAnyRole('FARMER')")
    @GetMapping("/{id}")
    public List<TransferEntity> getTransfer(@PathVariable int id) {
        return transferService.getBarnToFreezerTransfer(id);
    }

    @GetMapping("manager")
    public List<StaffDto> fetchAllManager() {
        return barnToFreezerService.fetchAllManager();
    }
}
