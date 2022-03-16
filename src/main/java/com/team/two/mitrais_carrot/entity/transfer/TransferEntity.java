package com.team.two.mitrais_carrot.entity.transfer;

import java.time.LocalDate;

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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sender_id")
    @NotNull
    private Long senderId;

    @Column(name = "receiver_id")
    @NotNull
    private Long receiverId;

    @Column(name = "time")
    @NotNull
    private LocalDate time;

    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private ETransferType type;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "carrot_amount")
    @NotNull
    private Long carrotAmount;
}
