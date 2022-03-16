package com.team.two.mitrais_carrot.service.merchant;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.group.UserGroupEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.dto.merchant.BazaarItemDto;

import com.team.two.mitrais_carrot.repository.BazaarItemRepository;

import com.team.two.mitrais_carrot.repository.BazaarRepository;
import com.team.two.mitrais_carrot.service.user.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class BazaarItemService{
    @Autowired
    BazaarItemRepository bazaarItemRepository;

    @Autowired
    BazaarRepository bazaarRepository;

    Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);

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
            newItem.setBazaar(checker);
            return bazaarItemRepository.save(newItem);
        }
        else{
            return null;
        }
//        newItem.setImage(request.getImage());
        //return null;
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

    public List<BazaarItemEntity> getItemInBazaar(int bazaarId){
        List<BazaarItemEntity> itemBazaar = new ArrayList<>();
        itemBazaar = bazaarItemRepository.findByBazaar_Id(bazaarId);
        return itemBazaar;
    }

    public void saveImage(int itemId, MultipartFile file) {
        BazaarItemEntity item = getById(itemId);
        try {
            item.setImageType(file.getContentType());
            item.setImageSize(file.getSize());
            item.setImage(file.getBytes());

            bazaarItemRepository.save(item);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bazaarItemRepository.save(item);
    }
}