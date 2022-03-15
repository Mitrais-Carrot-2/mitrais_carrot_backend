package com.team.two.mitrais_carrot.entity.transfer;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    @NotBlank
    private Long senderId;

    @Column(name = "receiver_id")
    @NotBlank
    private Long receiverId;

    @Column(name = "time")
    @NotBlank
    private LocalDate time;

    @Column(name = "type")
    @NotBlank
    @Enumerated(EnumType.STRING)
    private ETransferType type;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "carrot_amount")
    @NotBlank
    private Long carrotAmount;
}
