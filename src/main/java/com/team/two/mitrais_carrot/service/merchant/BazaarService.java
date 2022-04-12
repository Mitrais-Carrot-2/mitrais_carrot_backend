package com.team.two.mitrais_carrot.service.merchant;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.merchant.BazaarResponseDto;
import com.team.two.mitrais_carrot.dto.merchant.CreateBazaarDto;
import com.team.two.mitrais_carrot.entity.merchant.BazaarEntity;
import com.team.two.mitrais_carrot.repository.BazaarRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BazaarService {
    BazaarRepository bazaarRepository;

    public BazaarService(BazaarRepository bazaarRepository){
        this.bazaarRepository = bazaarRepository;
    }


    public List<BazaarEntity> getBazaar(){
        return (List<BazaarEntity>) bazaarRepository.findAll();
    }

    public List<BazaarResponseDto> getAllBazaarDto(){
        List<BazaarEntity> bazaars = getBazaar();
        List<BazaarResponseDto> listBazaarResponseDto = bazaars.stream()
                .map(bazaar -> new BazaarResponseDto(bazaar))
                .collect(Collectors.toList());
        return listBazaarResponseDto;
    }


    public ResponseEntity<?> updateBazaar(CreateBazaarDto request, Integer id){
//        try {
//            BazaarEntity bazaar = bazaarRepository.getById(id);
//            bazaar.setBazaarName(request.getBazaarName());
//            bazaar.setStartDate(request.getStartDate());
//            bazaar.setEndDate(request.getEndDate());
//            bazaarRepository.save(bazaar);
//            return ResponseEntity.ok(new MessageDto("Success Create New Bazaar", true));
//        } catch (Exception e){
//            return ResponseEntity.ok(new MessageDto("Failed", false));
//        }
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