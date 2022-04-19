package com.team.two.mitrais_carrot.entity.transfer;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transferHistories")
@ToString
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transferId;

    @Column(name = "basket_id")
    private int basketId = -1;

    @Column(name = "receiver_id")
    private Long receiverId;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "shareAt")
    private LocalDateTime shareAt;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ETransferType type;

    @Column(name = "note")
    private String note;

    @Column(name = "carrot_amount")
    private Long carrotAmount;
}
