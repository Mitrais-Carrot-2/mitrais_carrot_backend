package com.team.two.mitrais_carrot.entity.freezer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer freezerId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barn_id")
    private BarnEntity barnId;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "carrot_amount")
    private Long carrotAmount;

    @Column(name = "distributed_carrot")
    private Long distributedCarrot;

    @OneToMany(targetEntity = FreezerHistoryEntity.class, mappedBy = "freezerHistoryId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FreezerHistoryEntity> freezerHistoryId;

    public FreezerEntity(BarnEntity barnId, Long managerId, Long carrotAmount, Long distributedCarrot) {
        this.barnId = barnId;
        this.managerId = managerId;
        this.carrotAmount = carrotAmount;
        this.distributedCarrot = distributedCarrot;
    }
}
