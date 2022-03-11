package com.team.two.mitrais_carrot.entity.transferHistory;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Date time;

    @Column (name = "type")
    @NotBlank
    private String type;

    @Column(name = "desc")
    @NotBlank
    private String desc;

    @Column(name = "carrotAmount")
    @NotBlank
    private int carrotAmount;
}
