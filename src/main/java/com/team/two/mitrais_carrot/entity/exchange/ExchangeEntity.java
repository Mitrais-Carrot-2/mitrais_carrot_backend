package com.team.two.mitrais_carrot.entity.exchange;

import lombok.*;

import java.time.LocalDate;

import javax.persistence.*;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchanges")
public class ExchangeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status")
    private boolean status;

    @Column(name = "price")
    private int price;

    @Column(name = "exchangeDate")
    private LocalDate exchangeDate;

    @Column(name = "user_id")
    @OneToMany(mappedBy = "exchange")
    @JoinTable(
        name = "users",
        joinColumns = @JoinColumn(name = "exchange_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private UserEntity user_id;

    @Column(name = "bazaarItem_id")
    @OneToMany(mappedBy = "exchange")
    @JoinTable (
        name = "bazaarItems",
        joinColumns = @JoinColumn(name = "exchange_id"),
        inverseJoinColumns = @JoinColumn(name = "bazaarItem_id")
    )
    private BazaarEntity bazaaritem_id;
}
