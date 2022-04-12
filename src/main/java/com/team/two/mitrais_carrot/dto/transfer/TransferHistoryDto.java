package com.team.two.mitrais_carrot.dto.transfer;

import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferHistoryDto {
    private int transferId;
    private Long receiverId;
    private Long senderId;
    private String username;
    private LocalDateTime shareAt;
    private ETransferType type;
    private String note;
    private Long carrotAmount;
}
