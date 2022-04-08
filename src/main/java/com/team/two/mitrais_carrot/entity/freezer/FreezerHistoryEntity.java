package com.team.two.mitrais_carrot.entity.freezer;

import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "freezerHistories")
public class FreezerHistoryEntity {
    @Id
    @GeneratedValue
    private int freezerHistoryId;

    @Column(name = "carrot_amount")
    private Long carrotAmount;

    @Column(name = "share_at")
    private LocalDateTime shareAt;

    @ManyToOne
    @JoinColumn(name = "freezerId")
    private FreezerEntity freezerId;
}
