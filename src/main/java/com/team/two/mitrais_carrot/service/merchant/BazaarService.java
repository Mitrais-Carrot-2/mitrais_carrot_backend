package com.team.two.mitrais_carrot.service.merchant;

import com.team.two.mitrais_carrot.dto.merchant.CreateBazaarDto;
import com.team.two.mitrais_carrot.entity.merchant.BazaarEntity;
import com.team.two.mitrais_carrot.repository.BazaarRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BazaarService {
    BazaarRepository bazaarRepository;

    public BazaarService(BazaarRepository bazaarRepository){
        this.bazaarRepository = bazaarRepository;
    }

    public BazaarEntity createBazaar(CreateBazaarDto request){
        BazaarEntity bazaar = new BazaarEntity();
        bazaar.setBazaarName(request.getBazaarName());
        bazaar.setActive(true);
        bazaar.setStartDate(LocalDate.now());
        bazaar.setEndDate(LocalDate.of(2022,04,20));

        return bazaarRepository.save(bazaar);
    }

}       

}