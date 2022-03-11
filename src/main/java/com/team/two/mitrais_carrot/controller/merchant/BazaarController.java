package com.team.two.mitrais_carrot.controller.merchant;


import com.team.two.mitrais_carrot.dto.merchant.CreateBazaarDto;
import com.team.two.mitrais_carrot.entity.merchant.BazaarEntity;
import com.team.two.mitrais_carrot.service.merchant.BazaarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bazaar")
public class BazaarController {
    @Autowired
    BazaarService bazaarService;
    public BazaarController(BazaarService bazaarService){
        this.bazaarService = bazaarService;
    }

    @PostMapping("")
    public BazaarEntity createBazaar(@RequestBody CreateBazaarDto request){
        return bazaarService.createBazaar(request);
    }

}
