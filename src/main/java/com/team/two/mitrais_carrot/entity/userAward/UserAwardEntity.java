package com.team.two.mitrais_carrot.entity.userAward;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.award.AwardEntity;
import javax.persistence.*;
// import javax.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userAwards")
public class UserAwardEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "date")
  private LocalDate date;
//   @NotBlank

//  @Column(name = "user_id")
//  @OneToMany
//  @JoinTable(
//    name = "users",
//    joinColumns = @JoinColumn(name = "userAward_id"),
//    inverseJoinColumns = @JoinColumn(name = "user_id")
//  )
//  private UserEntity user_id;

//  @Column(name = "award_id")
//  @OneToMany
//  @JoinTable(
//    name = "awards",
//    joinColumns = @JoinColumn(name = "userAward_id"),
//    inverseJoinColumns = @JoinColumn(name = "award_id")
//  )
//  private AwardEntity award_id;
}
