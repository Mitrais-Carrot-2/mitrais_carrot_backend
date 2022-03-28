package com.team.two.mitrais_carrot.service.farmer;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.farmer.BarnDto;
import com.team.two.mitrais_carrot.dto.farmer.BarnEditDto;
import com.team.two.mitrais_carrot.entity.admin.BarnRewardEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.repository.farmer.BarnRepository;
import java.time.LocalDate;
import java.util.List;

import com.team.two.mitrais_carrot.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BarnService {
  private final BarnRepository barnRepository;

  public BarnService(BarnRepository barnRepository) {
    this.barnRepository = barnRepository;
  }

  public List<BarnEntity> fetchAllBarn() {
    return barnRepository.findAll();
  }

  public BarnEntity getBarnById(int id) {
    return barnRepository.findById(id).orElse(null); // TODO : Buat exception ketika Barn tdk ditemukan
  }

  Logger logger = LoggerFactory.getLogger(BarnService.class);

  public BarnEntity shareCarrot(Long carrotAmount, int barnId) {
    BarnEntity barn = getBarnById(barnId);
    Long remainingCarrot = barn.getCarrotAmount();
    if (remainingCarrot >= carrotAmount) {
      Long distributedCarrot = barn.getDistributedCarrot();
      barn.setDistributedCarrot(distributedCarrot + carrotAmount);
      barn.setCarrotAmount(remainingCarrot - carrotAmount);
      return barnRepository.save(barn);
    }
    return null; // TODO : handle insufficient carrot

  }

  public ResponseEntity<?> createBarn(BarnDto req) {
    if (barnRepository.existsByBarnName(req.getBarnName())) {
      return ResponseEntity.badRequest()
          .body(new MessageDto(String.format("Already created Barn with %s name", req.getBarnName()), false));
    }
    BarnEntity barnEntity = new BarnEntity();

    barnEntity.setUserId(getFarmerId());
    barnEntity.setBarnName(req.getBarnName());
    barnEntity.setStartDate(req.getStartDate());
    barnEntity.setEndDate(req.getEndDate());
    barnEntity.setCarrotAmount(req.getCarrotAmount());
    barnEntity.setIsActive(this.checkActive(req.getStartDate(), req.getEndDate()));

    barnRepository.save(barnEntity);
    return ResponseEntity.ok(new MessageDto(String.format("Successfully created %s Barn!", req.getBarnName()), true));
  }

  public BarnEntity isActiveBarn(boolean active) {
    return barnRepository.findByIsActive(active);
  }

  public Boolean checkActive(LocalDate startDate, LocalDate expiredDate) {
    return ((LocalDate.now().isEqual(startDate) ||
        LocalDate.now().isAfter(startDate)) &&
        LocalDate.now().isBefore(expiredDate));
  }

  public Long getFarmerId() {
    Long userId = 1L;
    try {
      UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      userId = user.getId();
    } catch (ClassCastException err) {
      userId = 1L;
    }

    return userId;
  }

  public ResponseEntity<?> updateBarn(int id, BarnEditDto req) {
    BarnEntity barnEntity = this.getBarnById(id);
    if (barnEntity == null) {
      return ResponseEntity.badRequest().body(new MessageDto("Barn not found", false));
    }
    barnEntity.setBarnName(req.getBarnName());
    barnEntity.setStartDate(req.getStartDate());
    barnEntity.setEndDate(req.getEndDate());
    barnEntity.setCarrotAmount(req.getCarrotAmount());
    barnEntity.setIsActive(this.checkActive(req.getStartDate(), req.getEndDate()));
    barnRepository.save(barnEntity);
    return ResponseEntity.ok(new MessageDto(String.format("Successfully updated %s Barn!", req.getBarnName()), true));

  }
  
  public ResponseEntity<?> addBarnReward (int id, BarnRewardEntity reward) {
    BarnEntity barnEntity = this.getBarnById(id);
    if (barnEntity == null) {
      return ResponseEntity.badRequest().body(new MessageDto("Barn not found", false));
    }
    barnEntity.getBarnReward().add(reward);
    barnRepository.save(barnEntity);
    return ResponseEntity.ok(new MessageDto(String.format("Successfully added reward to %s Barn!", barnEntity.getBarnName()), true));

  }

  }