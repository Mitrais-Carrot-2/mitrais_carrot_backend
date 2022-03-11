package com.team.two.mitrais_carrot.service.merchant;

import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.dto.merchant.BazaarItemDto;

import com.team.two.mitrais_carrot.repository.BazaarItemRepository;

import org.springframework.stereotype.Service;


@Service
public class BazaarItemService{
    BazaarItemRepository bazaarItemRepository;


    public BazaarItemEntity createBazaarItem(BazaarItemDto request){
        BazaarItemEntity item = new BazaarItemEntity();
        item.setBazaar_id(request.getBazaar_id());
        item.setActive(true);
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setQuantity(request.getQuantity());
        item.setDesc(request.getDesc());
        return null;
    }



}