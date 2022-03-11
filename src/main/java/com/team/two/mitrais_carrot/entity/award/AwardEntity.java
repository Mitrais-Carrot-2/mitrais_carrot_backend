package com.team.two.mitrais_carrot.entity.award;

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
@Table(name = "awards")
public class AwardEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status")
    @NotBlank
    private boolean status;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "desc")
    @NotBlank
    private String desc;

    @Column(name = "carrotAmount")
    @NotBlank
    private int carrotAmount;
}
