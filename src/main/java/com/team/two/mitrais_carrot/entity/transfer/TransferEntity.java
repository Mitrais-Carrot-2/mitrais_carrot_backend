package com.team.two.mitrais_carrot.entity.transfer;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

// import com.team.two.mitrais_carrot.entity.transfer.ETransferType;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transferHistories")
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transferId;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "receiver_id")
    private Long receiverId;

    @Column(name = "shareAt")
    private LocalDateTime shareAt;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ETransferType type;

    private String note;

    @Column(name = "carrot_amount")
    private Long carrotAmount;
}
