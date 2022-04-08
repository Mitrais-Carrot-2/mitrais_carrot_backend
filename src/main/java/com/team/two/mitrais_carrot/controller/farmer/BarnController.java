package com.team.two.mitrais_carrot.controller.farmer;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.farmer.BarnDto;
import com.team.two.mitrais_carrot.dto.farmer.BarnEditDto;
import com.team.two.mitrais_carrot.dto.farmer.BarnToFreezerDto;
import com.team.two.mitrais_carrot.dto.user.StaffDto;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.service.farmer.BarnService;
import com.team.two.mitrais_carrot.service.farmer.BarnToFreezerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
// @PreAuthorize("hasAnyRole('FARMER')")
@RequestMapping("/api/farmer/barn")
public class BarnController {
    @Autowired
    BarnService barnService;

    @Autowired
    BarnToFreezerService barnToFreezerService;
  

    @GetMapping("/")
    public List<BarnEntity> fetchBarn() {

    

//     @GetMapping("/")
// //    @PreAuthorize("hasAnyRole('FARMER')")
//     public List<BarnDto> fetchBarn(){

        return barnService.fetchAllBarn();
    }

    @PostMapping("/")
    public ResponseEntity<?> createBarn(@RequestBody BarnDto barnDto) {
        return barnService.createBarn(barnDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateBarn(@PathVariable("id") int id, @RequestBody BarnEditDto barnEditDto) {
        return barnService.updateBarn(id, barnEditDto);

//     // @PreAuthorize("hasAnyRole('FARMER')")
//     @PostMapping("/transfer")
//     public ResponseEntity<?> transfer(@RequestBody BarnToFreezerDto transferDto){
//         final Boolean status = barnToFreezerService.sendToManager(transferDto);
//         if(status){
//             return ResponseEntity.ok(new MessageDto("Transfer from Barn to Freezer success!", true));
//         } else {
//             return ResponseEntity.badRequest().body(new MessageDto("Not enough carrot in Barn!", false));
    }
}
