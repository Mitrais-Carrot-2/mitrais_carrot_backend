package com.team.two.mitrais_carrot.service.component;

import java.util.List;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.service.admin.BarnRewardService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    @Autowired
    BarnRewardService barnRewardService;

    Logger logger = org.slf4j.LoggerFactory.getLogger(Scheduler.class);
    @Scheduled(cron = "0 0 0 * * ?")
    public List<UserEntity> checkAtMidNightService(){
    
        logger.info("EXECUTE!");
        return barnRewardService.rewardByBirthDay();

    }
    
}
