package com.team.two.mitrais_carrot.service.award;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.award.CreateAwardDTO;
import com.team.two.mitrais_carrot.entity.award.AwardEntity;
import com.team.two.mitrais_carrot.repository.AwardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardService {
    private final AwardRepository awardRepository;

    public AwardService(AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
    }

    public List<AwardEntity> getAllAwards(){
        return awardRepository.findAll();
    }

    public ResponseEntity<?> createAward(CreateAwardDTO request){
        AwardEntity award = new AwardEntity();
        award.setName(request.getName());
        award.setCarrotAmount(request.getCarrotAmount());
        award.setDescription(request.getDescription());
        award.setExpiryDate(request.getExpiryDate());
        award.setStatus(request.isActive());
        awardRepository.save(award);
        return ResponseEntity.ok(new MessageDto("Success Create New Award!", true));
    }
}
