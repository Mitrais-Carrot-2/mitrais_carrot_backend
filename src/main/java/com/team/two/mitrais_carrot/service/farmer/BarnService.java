package com.team.two.mitrais_carrot.service.farmer;

import com.team.two.mitrais_carrot.dto.farmer.BarnDto;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.repository.farmer.BarnRepository;
import java.time.LocalDate;
import java.util.List;

import com.team.two.mitrais_carrot.security.services.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BarnService {
  private final BarnRepository barnRepository;

  public BarnService(BarnRepository barnRepository) {
      this.barnRepository = barnRepository;
  }

  public List<BarnEntity> fetchAllBarn(){
      return barnRepository.findAll();
  }

  public BarnEntity createBarn(BarnDto req) {
      BarnEntity barnEntity = new BarnEntity();
      UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      barnEntity.setBarnName(req.getBarnName());
      barnEntity.setIdUser(user.getId());
      barnEntity.setStartDate(req.getStartDate());
      barnEntity.setEndDate(req.getEndDate());
      barnEntity.setCarrotAmount(req.getCarrotAmount());

      return barnRepository.save(barnEntity);
  }

  public Boolean checkActive(LocalDate startDate, LocalDate expiredDate) {
    return (
      LocalDate.now().isAfter(startDate) &&
      LocalDate.now().isBefore(expiredDate)
    );
  }
}