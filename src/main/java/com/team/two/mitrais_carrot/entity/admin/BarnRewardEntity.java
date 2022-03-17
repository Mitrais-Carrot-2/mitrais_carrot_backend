package com.team.two.mitrais_carrot.entity.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "barn_rewards", uniqueConstraints={
    @UniqueConstraint(columnNames = "giving_conditional"),
    @UniqueConstraint(columnNames = "reward_description")
})
public class BarnRewardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reward_description")
    private String rewardDescription;

    @Column(name = "carrot_amount")
    private Long carrotAmount;

    @Column(name = "giving_conditional")
    @Enumerated(EnumType.STRING)
    private ETypeBarnReward givingConditional; //Sebagai referensi Cron job untuk memberi carrot. Referensi ada 3: Birth Day, Join Date dan end of the year

}
