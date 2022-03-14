package com.team.two.mitrais_carrot.service.merchant;

import com.team.two.mitrais_carrot.dto.merchant.CreateBazaarDto;
import com.team.two.mitrais_carrot.entity.merchant.BazaarEntity;
import com.team.two.mitrais_carrot.repository.BazaarRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BazaarService {
    BazaarRepository bazaarRepository;

    public BazaarService(BazaarRepository bazaarRepository){
        this.bazaarRepository = bazaarRepository;
    }


    public List<BazaarEntity> getBazaar(){

        return (List<BazaarEntity>) bazaarRepository.findAll();
    }

    public BazaarEntity createBazaar(CreateBazaarDto request){
        BazaarEntity bazaar = new BazaarEntity();
        bazaar.setBazaarName(request.getBazaarName());
//        bazaar.setActive(true);
        bazaar.setStartDate(request.getStartDate());
        bazaar.setEndDate(request.getEndDate());

        return bazaarRepository.save(bazaar);
    }

}