package com.team.two.mitrais_carrot.service.component;

import com.team.two.mitrais_carrot.service.admin.BarnRewardService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    BarnRewardService barnRewardService;
    @Scheduled(cron = "1 * 2 * * ?")
    public void checkAtMidNightService(){
        barnRewardService.rewardByBirthDay();

    }
    
}
