package com.team.two.mitrais_carrot.entity.basket;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import org.apache.catalina.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "baskets")
public class BasketEntity {
    @Id
//    @Column(name = "id")
    @GeneratedValue
    private int basketId;

    public BasketEntity(UserEntity userId, BarnEntity barnId, long carrotAmount, long rewardCarrot, long shareCarrot, long bazaarCarrot) {
        this.user = userId;
        this.barn = barnId;
        this.carrotAmount = carrotAmount;
        this.rewardCarrot = rewardCarrot;
        this.shareCarrot = shareCarrot;
        this.bazaarCarrot = bazaarCarrot;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barn_id")
    private BarnEntity barn;

    @Column(name = "carrotAmount")
    private long carrotAmount;

    @Column(name = "rewardCarrot")
    private long rewardCarrot;

    @Column(name = "sharedCarrot")
    private long shareCarrot;

    @Column(name = "bazaarCarrot")
    private long bazaarCarrot;
}