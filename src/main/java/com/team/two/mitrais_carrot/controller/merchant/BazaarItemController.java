package com.team.two.mitrais_carrot.controller.merchant;


import com.team.two.mitrais_carrot.dto.merchant.BazaarItemDto;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.service.merchant.BazaarItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bazaar/item")
public class BazaarItemController {
    @Autowired
    BazaarItemService bazaarItemService;

    public BazaarItemController(BazaarItemService bazaarItemService) { this.bazaarItemService = bazaarItemService; }

    @PostMapping("")
    public BazaarItemEntity addBazaarItem(@RequestBody BazaarItemDto request){
        return bazaarItemService.add(request);
    }

    @GetMapping("")
    public List<BazaarItemEntity> getAllBazaarItems(){
        return bazaarItemService.getAll();
    }

    @GetMapping("{id}")
    public BazaarItemEntity getBazaarItem(@PathVariable("id") String id){
        return bazaarItemService.getById(Integer.parseInt(id));
    }
}
