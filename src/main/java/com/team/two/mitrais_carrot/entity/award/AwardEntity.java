package com.team.two.mitrais_carrot.entity.award;

import lombok.*;
import javax.persistence.*;
// import javax.validation.constraints.NotBlank;

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
    // @NotBlank
    private boolean status;

    @Column(name = "name")
    // @NotBlank
    private String name;

    @Column(name = "desc")
    // @NotBlank
    private String desc;

    @Column(name = "carrotAmount")
    // @NotBlank
    private int carrotAmount;
}
