package com.team.two.mitrais_carrot.controller.merchant;


import com.team.two.mitrais_carrot.dto.merchant.BazaarItemDto;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.service.merchant.BazaarItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bazaar")
public class BazaarItemController {
    @Autowired
    BazaarItemService bazaarItemService;

    public BazaarItemController(BazaarItemService bazaarItemService) { this.bazaarItemService = bazaarItemService; }

    @PostMapping("{id}/item")
    public BazaarItemEntity addBazaarItem(@PathVariable("id") int id, @RequestBody BazaarItemDto request){
//        return bazaarItemService.add(request);
        return bazaarItemService.addNewItem(request,id);
    }

    @GetMapping("item")
    public List<BazaarItemEntity> getAllBazaarItems(){
        return bazaarItemService.getAll();
    }

    @GetMapping("{id}")
//    public BazaarItemEntity getBazaarItem(@PathVariable("id") String id){
//        return bazaarItemService.getById(Integer.parseInt(id));
//    }
    public List<BazaarItemEntity> getItemBazaar(@PathVariable("id") String id){
        return bazaarItemService.getItemInBazaar(Integer.parseInt(id));
    }
}
