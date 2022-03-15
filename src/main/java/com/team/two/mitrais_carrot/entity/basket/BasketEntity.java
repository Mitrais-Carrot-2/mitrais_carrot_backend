package com.team.two.mitrais_carrot.entity.basket;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "baskets")
public class BasketEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "barn_id")
    private long barnId;

    @Column(name = "carrotAmount")
    private long carrotAmount;

    @Column(name = "rewardCarrot")
    private long rewardCarrot;

    @Column(name = "sharedCarrot")
    private long shareCarrot;

    @Column(name = "bazaarCarrot")
    private long bazaarCarrot;
}