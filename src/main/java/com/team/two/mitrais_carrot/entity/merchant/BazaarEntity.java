package com.team.two.mitrais_carrot.entity.merchant;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer"})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BazaarEntity")
@Table(name = "bazaars", uniqueConstraints = {@UniqueConstraint(columnNames = "bazaarName")})
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

//    @OneToMany(mappedBy = "bazaar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<BazaarItemEntity> bazaarItems = new ArrayList<>();
}