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

    @Column(name = "carrotAmount")
    private long carrotAmount;

    @Column(name = "rewardCarrot")
    private long rewardCarrot;

    @Column(name = "sharedCarrot")
    private long sharedCarrot;

    @Column(name = "bazaarCarrot")
    private long bazaarCarrot;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "barn_id")
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "barns", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "barn_id"))
    private int barnId;

//    public boolean getIsActive() {
//    }

//    @Column(name = "userBasket_id")
//    @JoinColumn(name = "userBasket_id")
//    @ManyToOne
//    @JoinTable(name = "userBaskets", joinColumns = @JoinColumn(name = "baskets_id"), inverseJoinColumns = @JoinColumn(name = "userBasket_id"))
//    private UserEntity userBasket_id;
}