package com.team.two.mitrais_carrot.entity.merchant;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer"})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BazaarEntity")
@Table(name = "bazaars")
public class BazaarEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "bazaarName")
    private String bazaarName;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

//    @OneToMany(targetEntity = BazaarEntity.class, mappedBy = bazaarId, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<BazaarEntity> bazaarId;
}