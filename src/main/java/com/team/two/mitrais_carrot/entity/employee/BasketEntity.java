package com.team.two.mitrais_carrot.entity.employee;

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

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "expiryDate")
    private LocalDate expiryDate;

    @Column(name = "isActive")
    private boolean isActive;

//    @Column(name = "userBasket_id")
//    @JoinColumn(name = "userBasket_id")
//    @ManyToOne
//    @JoinTable(name = "userBaskets", joinColumns = @JoinColumn(name = "baskets_id"), inverseJoinColumns = @JoinColumn(name = "userBasket_id"))
//    private UserEntity userBasket_id;
}