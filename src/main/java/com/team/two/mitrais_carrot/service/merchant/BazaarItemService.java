package com.team.two.mitrais_carrot.service.merchant;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.merchant.BazaarItemResponseDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.group.UserGroupEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.dto.merchant.BazaarItemDto;

import com.team.two.mitrais_carrot.repository.BazaarItemRepository;

import com.team.two.mitrais_carrot.repository.BazaarRepository;
import com.team.two.mitrais_carrot.service.user.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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

    public ResponseEntity<?> add(BazaarItemDto request){
        BazaarItemEntity item = new BazaarItemEntity();
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setQuantity(request.getQuantity());
        bazaarItemRepository.save(item);
        return ResponseEntity.ok(new MessageDto("Success Add Item to the Bazaar!", true));
    }

    public ResponseEntity<?> addNewItem(BazaarItemDto request, Integer bazaarId){
        BazaarItemEntity newItem = new BazaarItemEntity();
        BazaarEntity checker = bazaarRepository.getById(bazaarId);
        if(checker.getBazaarName()!=null){
            newItem.setName(request.getName());
            newItem.setPrice(request.getPrice());
            newItem.setQuantity(request.getQuantity());
            newItem.setDescription(request.getDescription());
            newItem.setBazaar(checker);
            bazaarItemRepository.save(newItem);
            return ResponseEntity.ok(new MessageDto("Success Add Item to the Bazaar!", true));
        }
        else{
            return ResponseEntity.badRequest().body(new MessageDto("Fail to Add Item!", false));
        }
//        newItem.setImage(request.getImage());
        //return null;
    }

    public List<BazaarItemEntity> getAll(){
        return bazaarItemRepository.findAll();
    }

    public List<BazaarItemEntity> getAllItemsInBazaar(int bazaarId){
        List<BazaarItemEntity> itemBazaar = new ArrayList<>();
        itemBazaar = bazaarItemRepository.findByBazaar_Id(bazaarId);
        return itemBazaar;
    }

    public BazaarItemEntity getById(int id){
        return bazaarItemRepository.findById(id).orElse(null);
    }

    public BazaarItemEntity getByIdInBazaar(int itemId, int bazaarId){
        return bazaarItemRepository.findByIdAndBazaar_Id(itemId, bazaarId);
    }

    public List<BazaarItemResponseDto> getAllDto() {
        List<BazaarItemEntity> listBazaarItemEntity = getAll();
        return listBazaarItemEntity.stream()
                .map(item -> new BazaarItemResponseDto(item))
                .collect(Collectors.toList());
    }

    public List<BazaarItemResponseDto> getAllDtoInBazaar(int bazaarId) {
        List<BazaarItemEntity> listBazaarItemEntity = getAllItemsInBazaar(bazaarId);
        return listBazaarItemEntity.stream()
                .map(item -> new BazaarItemResponseDto(item))
                .collect(Collectors.toList());
    }

    public BazaarItemResponseDto getDtoById(int itemId) {
        return new BazaarItemResponseDto(getById(itemId));
    }

    public BazaarItemResponseDto getDtoByIdInBazaar(int itemId, int bazaarId) {
        return new BazaarItemResponseDto(getByIdInBazaar(itemId, bazaarId));
    }

    public ResponseEntity<?> updateQuantity(int itemId, int addQty){
        BazaarItemEntity item = getById(itemId);
        int newQty = item.getQuantity() + addQty;
        if (newQty < 0) newQty = 0;
        item.setQuantity(newQty);
        bazaarItemRepository.save(item);
        return ResponseEntity.ok(new MessageDto("Success Update Item Quantity!", true));
    }

    public ResponseEntity<?> updateItem(int itemId, BazaarItemDto request){
        BazaarItemEntity item = getById(itemId);
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setQuantity(request.getQuantity());
        item.setDescription(request.getDescription());
        bazaarItemRepository.save(item);
        return ResponseEntity.ok(new MessageDto("Success Update Item!", true));
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

    public ResponseEntity<?> deleteById(int itemId, int bazaarId){
        BazaarItemEntity entity = bazaarItemRepository.findByIdAndBazaar_Id(itemId, bazaarId);
        if (!Objects.isNull(entity)) {
            bazaarItemRepository.deleteById(itemId);
            return ResponseEntity.ok(new MessageDto("[SUCCESS] Deleting item from bazaar", true));
        }
        return ResponseEntity.ok(new MessageDto("[FAILED] Item not found", false));
    }

}