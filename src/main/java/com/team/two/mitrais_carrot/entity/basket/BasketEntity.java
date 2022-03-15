package com.team.two.mitrais_carrot.entity.basket;

import java.time.LocalDate;

import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
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
@Table(name = "baskets", schema = "public")
public class BasketEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    public BasketEntity(UserEntity userId, BarnEntity barnId, long carrotAmount, long rewardCarrot, long shareCarrot, long bazaarCarrot) {
        this.userId = userId;
        this.barnId = barnId;
        this.carrotAmount = carrotAmount;
        this.rewardCarrot = rewardCarrot;
        this.shareCarrot = shareCarrot;
        this.bazaarCarrot = bazaarCarrot;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barn_id", nullable = true)
    private BarnEntity barnId;

    @Column(name = "carrotAmount")
    private long carrotAmount;

    @Column(name = "rewardCarrot")
    private long rewardCarrot;

    @Column(name = "sharedCarrot")
    private long shareCarrot;

    @Column(name = "bazaarCarrot")
    private long bazaarCarrot;
}