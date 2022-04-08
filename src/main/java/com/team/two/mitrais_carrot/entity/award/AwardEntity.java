package com.team.two.mitrais_carrot.entity.award;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
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
    private boolean status;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "carrot_amount")
    private Long carrotAmount;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;
}
