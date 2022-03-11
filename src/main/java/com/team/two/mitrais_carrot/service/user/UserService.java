package com.team.two.mitrais_carrot.service.user;

import com.team.two.mitrais_carrot.repository.BazaarItemRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private BazaarItemRepository bazaarItemRepository;
    private int status;

    public UserService(BazaarItemRepository bazaarItemRepository) {this.bazaarItemRepository = bazaarItemRepository;}

    public BazaarItemEntity fetchById(long id){
        return bazaarItemRepository.findById(id).orElse(null);
    }

    public int buyBazaarItem(long id){
        BazaarItemEntity item = fetchById(id);
        if (item == null) return 404;
        else return 200;
    }
    

}