package com.team.two.mitrais_carrot.entity.freezer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import lombok.*;
import org.springframework.http.ResponseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "freezers")
public class FreezerEntity {
    @Id
//    @Column(name = "freezer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer freezerId;

    @ManyToOne
    @JoinColumn(name = "barnId")
    private BarnEntity barnId;

    @ManyToOne
    @JoinColumn(name = "basketId")
    private BasketEntity basketId;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "carrot_amount")
    private Long carrotAmount;

    @Column(name = "distributed_carrot")
    private int distributedCarrot;
}
