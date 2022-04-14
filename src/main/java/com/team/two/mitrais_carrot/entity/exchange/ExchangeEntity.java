package com.team.two.mitrais_carrot.entity.exchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import lombok.*;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;

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

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private BazaarItemEntity bazaarItemId;

    @Column(name = "status")
    private EExchangeStatus status;

}
