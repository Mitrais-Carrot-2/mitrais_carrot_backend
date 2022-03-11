package com.team.two.mitrais_carrot.entity.merchant;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bazaars")
public class BazaarEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "bazaarName")
    private String bazaarName;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

}