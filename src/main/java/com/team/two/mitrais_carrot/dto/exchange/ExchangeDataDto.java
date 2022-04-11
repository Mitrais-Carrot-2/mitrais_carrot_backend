package com.team.two.mitrais_carrot.dto.exchange;

import com.team.two.mitrais_carrot.entity.exchange.EExchangeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDataDto {
    private int id;
    private boolean isActive;
    private long price;
    private LocalDateTime exchangeDate;
    private EExchangeStatus status;
    private String buyer;
    private String item;

//    public ExchangeDataDto(long id, boolean active, long price, LocalDateTime exchangeDate, EExchangeStatus status, String s, String name) {
//
//    }
}
