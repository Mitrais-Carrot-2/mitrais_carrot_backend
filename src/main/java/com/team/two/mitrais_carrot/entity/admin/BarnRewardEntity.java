package com.team.two.mitrais_carrot.entity.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "barn_rewards")
public class BarnRewardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "barn_id")
    // TODO : Buat relasi ke Tabel Barn
    private int barnId; // FK ke Barn, Many to one

    @Column(name = "reward_description")
    private String rewardDescription;

    @Column(name = "carrot_amount")
    private int carrotAmount;

    @Column(name = "giving_conditional")
    private String givingConditional; //Sebagai referensi Cron job untuk memberi carrot. Referensi ada 3: Birth Day, Join Date dan end of the year

}
