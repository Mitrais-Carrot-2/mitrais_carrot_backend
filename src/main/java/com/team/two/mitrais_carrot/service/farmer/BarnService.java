package com.team.two.mitrais_carrot.service.farmer;

import com.team.two.mitrais_carrot.dto.farmer.BarnDto;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.repository.BasketRepository;
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

  public BarnEntity getBarnById(int id){
    return barnRepository.findById(id).orElse(null); // TODO : Buat exception ketika Barn tdk ditemukan
  }

  public BarnEntity shareCarrot(Long carrotAmount, int barnId){
    BarnEntity barn = getBarnById(barnId);
    Long remainingCarrot = barn.getCarrotAmount();
    if (remainingCarrot >= carrotAmount){
      Long distributedCarrot = barn.getDistributedCarrot();
      barn.setDistributedCarrot(distributedCarrot + carrotAmount);
      barn.setCarrotAmount(remainingCarrot - carrotAmount);
      return barnRepository.save(barn);
    }
    return null; // TODO : handle insufficient carrot
    
  }

  public BarnEntity createBarn(BarnDto req) {
      BarnEntity barnEntity = new BarnEntity();
//      UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//      barnEntity.setIdUser(user.getId());
      barnEntity.setIdUser(1L);
      barnEntity.setBarnName(req.getBarnName());
      barnEntity.setStartDate(req.getStartDate());
      barnEntity.setEndDate(req.getEndDate());
      barnEntity.setCarrotAmount(req.getCarrotAmount());
      barnEntity.setIsActive(this.checkActive(req.getStartDate(), req.getEndDate()));

      List<BarnEntity> activeBarn = barnRepository.findByIsActiveTrue();
      if (activeBarn!=null){
          activeBarn.forEach(a -> {
              a.setIsActive(false);
          });
          barnRepository.saveAll(activeBarn);
      }
      return barnRepository.save(barnEntity);
  }

  public BarnEntity isActiveBarn(boolean active){
      return barnRepository.findByIsActive(active);
  }

  public Boolean checkActive(LocalDate startDate, LocalDate expiredDate) {
    return (
      (LocalDate.now().isEqual(startDate) ||
      LocalDate.now().isAfter(startDate)) &&
      LocalDate.now().isBefore(expiredDate)
    );
  }
}