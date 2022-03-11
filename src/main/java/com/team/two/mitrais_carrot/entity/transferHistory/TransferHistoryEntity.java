package com.team.two.mitrais_carrot.entity.transferHistory;

import java.util.LocalDate;

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

    @Column(name = "desc")
    @NotBlank
    private String desc;

    @Column(name = "carrotAmount")
    @NotBlank
    private int carrotAmount;
}
