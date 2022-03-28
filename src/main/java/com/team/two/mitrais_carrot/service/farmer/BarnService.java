package com.team.two.mitrais_carrot.service.farmer;

import com.team.two.mitrais_carrot.dto.farmer.BarnDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.repository.farmer.BarnRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.team.two.mitrais_carrot.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class BarnService {
    @Autowired
    UserRepository userRepository;

  private final BarnRepository barnRepository;

  public BarnService(BarnRepository barnRepository) {
      this.barnRepository = barnRepository;
  }

  public List<BarnDto> fetchAllBarn(){
      List<BarnEntity> barns = barnRepository.findAll();
      List<BarnDto> barnDto = new ArrayList<>();

      barns.forEach(b -> {
          barnDto.add(new BarnDto(b.getId(), b.getBarnName(), b.getCarrotAmount(), b.getStartDate(), b.getEndDate()));
      });
      return barnDto;
  }

  public BarnEntity getBarnById(int id){
    return barnRepository.findById(id).orElse(null); // TODO : Buat exception ketika Barn tdk ditemukan
  }

  Logger logger = LoggerFactory.getLogger(BarnService.class);

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

      barnEntity.setUserId(getFarmerId());
      barnEntity.setBarnName(req.getBarnName());
      barnEntity.setStartDate(req.getStartDate());
      barnEntity.setEndDate(req.getEndDate());
      barnEntity.setCarrotAmount(req.getCarrotAmount());
      barnEntity.setIsActive(this.checkActive(req.getStartDate(), req.getEndDate()));

//      List<BarnEntity> activeBarn = barnRepository.findByIsActiveTrue();
//      if (activeBarn!=null){
//          activeBarn.forEach(a -> {
//              a.setIsActive(false);
//          });
//          barnRepository.saveAll(activeBarn);
//      }
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

    public UserEntity getFarmerId() {
        String username = "";
        try {
            UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            username = user.getUsername();
        } catch (ClassCastException err) {
            username = "";
        }

        return userRepository.findByUsername(username);
    }
}