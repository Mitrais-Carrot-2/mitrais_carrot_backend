//package com.team.two.mitrais_carrot.controller.farmer;
//
//import com.team.two.mitrais_carrot.dto.MessageDto;
//import com.team.two.mitrais_carrot.dto.farmer.BarnToFreezerDto;
//import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
//import com.team.two.mitrais_carrot.service.farmer.BarnToFreezerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
////@PreAuthorize("hasAnyRole('FARMER')")
//@RequestMapping("/api/farmer/transfer")
//public class BarnToFreezerController {
//    @Autowired
//    BarnToFreezerService barnToFreezerService;
//
//    @PostMapping("")
//    public ResponseEntity<?> transfer(@RequestBody BarnToFreezerDto transferDto){
//        final Boolean status = barnToFreezerService.sendToManager(transferDto);
//        if(status){
//            return ResponseEntity.ok(new MessageDto("Transfer from Barn to Freezer success!", true));
//        } else {
//            return ResponseEntity.badRequest().body(new MessageDto("Not enough carrot in Barn!", false));
//        }
//    }
//}
