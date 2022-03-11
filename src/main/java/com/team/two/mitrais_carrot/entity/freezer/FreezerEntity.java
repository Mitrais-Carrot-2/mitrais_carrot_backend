package com.team.two.mitrais_carrot.entity.freezer;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Date startDate;

    @Column(name = "expiredDate")
    @NotBlank
    private Date expiredDate;

    @Column(name = "isActive")
    @NotBlank
    private boolean isActive;

    @Column(name = "user_id")
    @OneToMany(mappedBy = "freezer")
    @JoinTable(name = "users", joinColumns = @JoinColumn(name = "freezer_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private UserEntity user_id;

    
}
