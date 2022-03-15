package com.team.two.mitrais_carrot.service.merchant;

import com.team.two.mitrais_carrot.entity.group.UserGroupEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.dto.merchant.BazaarItemDto;

import com.team.two.mitrais_carrot.repository.BazaarItemRepository;

import com.team.two.mitrais_carrot.repository.BazaarRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BazaarItemService{
    BazaarItemRepository bazaarItemRepository;
    BazaarRepository bazaarRepository;

    public BazaarItemService(BazaarItemRepository bazaarItemRepository, BazaarRepository bazaarRepository){
        this.bazaarItemRepository = bazaarItemRepository;
        this.bazaarRepository = bazaarRepository;
    }

    public BazaarItemEntity add(BazaarItemDto request){
        BazaarItemEntity item = new BazaarItemEntity();
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setQuantity(request.getQuantity());
        return bazaarItemRepository.save(item);
    }

    public BazaarItemEntity addNewItem(BazaarItemDto request, Integer bazaarId){
        BazaarItemEntity newItem = new BazaarItemEntity();
        BazaarEntity checker = bazaarRepository.getById(bazaarId);
        if(checker.getBazaarName()!=null){
            newItem.setName(request.getName());
            newItem.setPrice(request.getPrice());
            newItem.setQuantity(request.getQuantity());
            newItem.setDescription(request.getDescription());
            newItem.setBazaar_id(checker);
        }

//        newItem.setImage(request.getImage());

        return bazaarItemRepository.save(newItem);
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