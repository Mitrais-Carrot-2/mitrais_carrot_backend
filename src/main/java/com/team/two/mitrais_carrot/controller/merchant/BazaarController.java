package com.team.two.mitrais_carrot.controller.merchant;


import com.team.two.mitrais_carrot.dto.merchant.CreateBazaarDto;
import com.team.two.mitrais_carrot.entity.merchant.BazaarEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.service.merchant.BazaarService;
import com.team.two.mitrais_carrot.service.merchant.BazaarItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/bazaar")
public class BazaarController {
    @Autowired
    BazaarService bazaarService;
    public BazaarController(BazaarService bazaarService){
        this.bazaarService = bazaarService;
    }

    BazaarItemService bazaarItemService;
    public BazaarController(BazaarItemService bazaarItemService){
        this.bazaarItemService = bazaarItemService;
    }

    @PostMapping("")
    public BazaarEntity createBazaar(@RequestBody CreateBazaarDto request){
        return bazaarService.createBazaar(request);
    }

    @PostMapping("/item")
    public BazaarItemEntity createBazaarItem(@RequestBody BazaarItemDto request){
        return bazaarItemService.createBazaarItem(request);
    }
}