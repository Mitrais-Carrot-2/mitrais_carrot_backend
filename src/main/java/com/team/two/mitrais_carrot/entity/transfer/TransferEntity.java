package com.team.two.mitrais_carrot.entity.transfer;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

// import com.team.two.mitrais_carrot.entity.transfer.ETransferType;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "receiver_id")
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
