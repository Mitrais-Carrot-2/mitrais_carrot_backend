package com.team.two.mitrais_carrot.entity.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "barn_rewards")
public class BarnRewardEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reward_description")
    @NotNull
    private String rewardDescription;

    @Column(name = "carrot_amount")
    @NotNull
    private Long carrotAmount;

    @Column(name = "giving_conditional")
    @NotNull
    @Enumerated(EnumType.STRING)
    private ETypeBarnReward givingConditional; // Sebagai referensi Cron job untuk memberi carrot. Referensi ada 3:
                                               // Birth Day, Join Date dan end of the year

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barn_id")
    private BarnEntity barn;

}
