package com.team.two.mitrais_carrot.controller.merchant;


import com.team.two.mitrais_carrot.dto.merchant.BazaarItemDto;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.service.merchant.BazaarItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bazaar/item")
public class BazaarItemController {
    @Autowired
    BazaarItemService bazaarItemService;
    public BazaarItemController(BazaarItemService bazaarItemService){
        this.bazaarItemService = bazaarItemService;
    }

    @PostMapping("")
    public BazaarItemEntity createBazaarItem(@RequestBody BazaarItemDto request){
        return bazaarItemService.createBazaarItem(request);
    }
}
