package com.team.two.mitrais_carrot.entity.freezer;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.*;

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

  @Column(name = "carrotAmount")
  @NotBlank
  private int carrotAmount;

  @Column(name = "startDate")
  @NotBlank
  private LocalDate startDate;

  @Column(name = "expiredDate")
  @NotBlank
  private LocalDate expiredDate;

  @Column(name = "isActive")
  @NotBlank
  private boolean isActive;

  @Column(name = "user_id")
  @OneToMany(mappedBy = "freezer")
  @JoinTable(
    name = "users",
    joinColumns = @JoinColumn(name = "freezer_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private UserEntity user_id;
}
