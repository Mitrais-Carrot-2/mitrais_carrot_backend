package com.team.two.mitrais_carrot.service.merchant;

import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.dto.merchant.BazaarItemDto;

import com.team.two.mitrais_carrot.repository.BazaarItemRepository;

import org.springframework.stereotype.Service;


@Service
public class BazaarItemService{
    BazaarItemRepository bazaarItemRepository;
    public BazaarItemService(BazaarItemRepository bazaarItemRepository){
        this.bazaarItemRepository = bazaarItemRepository;
    }

    public BazaarItemEntity createBazaarItem(BazaarItemDto request){
        BazaarItemEntity item = new BazaarItemEntity();
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setQuantity(request.getQuantity());
        return bazaarItemRepository.save(item);
    }

    public BazaarItemEntity fetchById(long id){
        return bazaarItemRepository.findById((int) id).orElse(null);
    }


}