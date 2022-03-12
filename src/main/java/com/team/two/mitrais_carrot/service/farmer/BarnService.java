//package com.team.two.mitrais_carrot.service.farmer;
//
//import com.team.two.mitrais_carrot.dto.farmer.CreateBarnDto;
//import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
//import com.team.two.mitrais_carrot.repository.BarnRepository;
//import java.time.LocalDate;
//import org.springframework.stereotype.Service;
//import lombok.*;
//
//@Service
//public class BarnService {
//
//  private final BarnRepository barnRepo;
//
//  public BarnService(BarnRepository barnRepository) {
//    this.barnRepo = barnRepository;
//  }
//
//  public BarnEntity createBarn(CreateBarnDto request) {
//    BarnEntity barn = new BarnEntity();
//    barn.setBarnName(request.getBarnName());
//    barn.setCarrotAmount(request.getCarrotAmount());
//    barn.setStartDate(request.getStartDate());
//    barn.setExpiredDate(request.getExpiredDate());
//    barn.setIsActive(checkActive(barn.getStartDate(), barn.getExpiredDate()));
//
//    return barnRepo.save(barn);
//  }
//
//  public Boolean checkActive(LocalDate startDate, LocalDate expiredDate) {
//    return (
//      LocalDate.now().isAfter(startDate) &&
//      LocalDate.now().isBefore(expiredDate)
//    );
//  }
//}