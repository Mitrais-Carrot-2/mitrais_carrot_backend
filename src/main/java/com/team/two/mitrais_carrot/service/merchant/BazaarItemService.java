package com.team.two.mitrais_carrot.service.merchant;

import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.dto.merchant.BazaarItemDto;

import com.team.two.mitrais_carrot.repository.BazaarItemRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BazaarItemService{
    BazaarItemRepository bazaarItemRepository;
    public BazaarItemService(BazaarItemRepository bazaarItemRepository){
        this.bazaarItemRepository = bazaarItemRepository;
    }

    public BazaarItemEntity add(BazaarItemDto request){
        BazaarItemEntity item = new BazaarItemEntity();
        item.setName(request.getName());
        item.setPrice((long) request.getPrice());
        item.setQuantity(request.getQuantity());
        return bazaarItemRepository.save(item);
    }

    public List<BazaarItemEntity> getAll(){
        return bazaarItemRepository.findAll();
    }

    public BazaarItemEntity getById(int id){
        return bazaarItemRepository.findById(id).orElse(null);
    }

    public BazaarItemEntity updateQuantity(int itemId, int addQty){
        BazaarItemEntity item = getById(itemId);
        int newQty = item.getQuantity() + addQty;
        if (newQty < 0) newQty = 0;
        item.setQuantity(newQty);

        return bazaarItemRepository.save(item);
    }

}