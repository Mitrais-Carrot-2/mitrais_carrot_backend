package com.team.two.mitrais_carrot.controller.merchant;

import com.team.two.mitrais_carrot.dto.merchant.BazaarResponseDto;
import com.team.two.mitrais_carrot.dto.merchant.CreateBazaarDto;
import com.team.two.mitrais_carrot.service.merchant.BazaarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bazaar")
public class BazaarController {
    @Autowired
    BazaarService bazaarService;

    public BazaarController(BazaarService bazaarService) {
        this.bazaarService = bazaarService;
    }

    @GetMapping("")
    public List<BazaarResponseDto> getAllBazaar() {
        return bazaarService.getAllBazaarDto();
    }

    @GetMapping("active")
    public List<BazaarResponseDto> getActiveBazaar() {
        return bazaarService.getActiveBazaar();
    }

    @PostMapping("")
    public ResponseEntity<?> createBazaar(@RequestBody CreateBazaarDto request) {
        return bazaarService.createBazaar(request);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBazaar(@PathVariable("id") int id, @RequestBody CreateBazaarDto request) {
        return bazaarService.updateBazaar(request, id);
    }
}
