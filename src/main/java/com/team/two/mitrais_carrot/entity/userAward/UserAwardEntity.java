package com.team.two.mitrais_carrot.entity.userAward;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.award.AwardEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @NotBlank
    private Date type;

    @Column(name = "user_id")
    @OneToMany(mappedBy = "userAward")
    @JoinTable(name = "users", joinColumns = @JoinColumn(name = "userAward_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private UserEntity user_id;

    @Column(name = "award_id")
    @OneToMany(mappedBy = "userAward")
    @JoinTable(name = "awards", joinColumns = @JoinColumn(name = "userAward_id"), inverseJoinColumns = @JoinColumn(name = "award_id"))
    private AwardEntity award_id;
    
}
