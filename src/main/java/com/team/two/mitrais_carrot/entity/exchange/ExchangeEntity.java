package com.team.two.mitrais_carrot.entity.exchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private long id;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "price")
    private long price;

    @Column(name = "exchange_date")
    private LocalDateTime exchangeDate;

//    @Column(name="user_id")
//    private long userId;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userId;

//    @Column(name="bazaarItem_id")
//    private int bazaarItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private BazaarItemEntity bazaarItemId;

    @Column(name="status")
    private EExchangeStatus status;

//    @Column(name = "user_id")
//    @OneToMany(mappedBy = "exchange")
//    @JoinTable(
//        name = "users",
//        joinColumns = @JoinColumn(name = "exchange_id"),
//        inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private UserEntity user_id;

//    @Column(name = "bazaarItem_id")
//    @OneToMany(mappedBy = "exchange")
//    @JoinTable (
//        name = "bazaarItems",
//        joinColumns = @JoinColumn(name = "exchange_id"),
//        inverseJoinColumns = @JoinColumn(name = "bazaarItem_id")
//    )
//    private BazaarEntity bazaaritem_id;
}
