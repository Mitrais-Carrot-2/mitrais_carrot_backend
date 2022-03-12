package com.team.two.mitrais_carrot.service.exchange;

import com.team.two.mitrais_carrot.dto.auth.UserDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.repository.BazaarItemRepository;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.service.merchant.BazaarItemService;

import org.springframework.stereotype.Service;

@Service
public class ExchangeService {
    private BazaarItemRepository bazaarItemRepository;
    private UserRepository userRepository;

    private BazaarItemService bazaarItemService;
    
    private int status;

    public ExchangeService(BazaarItemRepository bazaarItemRepository) {this.bazaarItemRepository = bazaarItemRepository;}


    public UserEntity fetchById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public int buyBazaarItem(UserDto user, int itemId){
        UserEntity users = fetchById(user.getId());
        BazaarItemEntity item = bazaarItemService.fetchById(itemId);
        if (users != null){
            if (item == null) status = 404;
            else {
                status = 200;
            }
        }

        return status;
    }
    

}