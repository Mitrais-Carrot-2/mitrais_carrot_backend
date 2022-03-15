package com.team.two.mitrais_carrot.entity.freezer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "barn_id")
    private Integer barnId;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "carrot_amount")
    private Long carrotAmount;

    @Column(name = "distributed_carrot")
    private int distributedCarrot;

    @Column(name = "share_at")
    private LocalDateTime shareAt;
}
