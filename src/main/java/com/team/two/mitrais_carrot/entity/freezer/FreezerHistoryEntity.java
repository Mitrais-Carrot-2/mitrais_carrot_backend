package com.team.two.mitrais_carrot.entity.freezer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "freezerHistories")
public class FreezerHistoryEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "barn_id")
    private Integer barnId;

    @Column(name = "carrot_amount")
    private Long carrotAmount;

    @Column(name = "share_at")
    private LocalDateTime shareAt;
}
