package com.team.two.mitrais_carrot.service.merchant;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.merchant.CreateBazaarDto;
import com.team.two.mitrais_carrot.entity.merchant.BazaarEntity;
import com.team.two.mitrais_carrot.repository.BazaarRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> updateBazaar(CreateBazaarDto request, Integer id){
        BazaarEntity bazaar = bazaarRepository.getById(id);
        bazaar.setBazaarName(request.getBazaarName());
        bazaar.setStartDate(request.getStartDate());
        bazaar.setEndDate(request.getEndDate());
        bazaarRepository.save(bazaar);
        return ResponseEntity.ok(new MessageDto("Success Create New Bazaar", true));
    }

    public ResponseEntity<?> createBazaar(CreateBazaarDto request){
        BazaarEntity bazaar = new BazaarEntity();
        bazaar.setBazaarName(request.getBazaarName());
//        bazaar.setActive(true);
        bazaar.setStartDate(request.getStartDate());
        bazaar.setEndDate(request.getEndDate());
        bazaarRepository.save(bazaar);

        return ResponseEntity.ok(new MessageDto("Success Create New Bazaar", true));
    }

}