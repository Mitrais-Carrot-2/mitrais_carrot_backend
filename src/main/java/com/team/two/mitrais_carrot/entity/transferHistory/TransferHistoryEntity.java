package com.team.two.mitrais_carrot.entity.transferHistory;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transferHistories")
public class TransferHistoryEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "time")
    @NotBlank
    private LocalDate time;

    @Column(name = "type")
    @NotBlank
    private String type;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "carrot_amount")
    @NotBlank
    private int carrotAmount;
}
