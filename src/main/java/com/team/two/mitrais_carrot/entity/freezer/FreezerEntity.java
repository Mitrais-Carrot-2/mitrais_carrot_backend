package com.team.two.mitrais_carrot.entity.freezer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.transfer.FreezerTransferHistoryEntity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "freezers")
public class FreezerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barn_id")
    private BarnEntity barn;

    @Column(name = "manager_id")
    private Long managerId;
    // @ManyToOne
    // @JoinColumn(name = "manager_id")
    // private UserEntity managerId;

    @Column(name = "carrot_amount")
    private Long carrotAmount;

    @Column(name = "distributed_carrot")
    private Long distributedCarrot;

    @OneToMany(targetEntity = FreezerHistoryEntity.class, mappedBy = "freezerHistoryId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FreezerHistoryEntity> freezerHistoryId;

    @JsonIgnore
    @OneToMany(mappedBy = "freezer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FreezerTransferHistoryEntity> freezerTransferHistories = new ArrayList<>();

    public FreezerEntity(BarnEntity barn, Long managerId, Long carrotAmount, Long distributedCarrot) {
        this.barn = barn;
        this.managerId = managerId;
        this.carrotAmount = carrotAmount;
        this.distributedCarrot = distributedCarrot;
    }
}
