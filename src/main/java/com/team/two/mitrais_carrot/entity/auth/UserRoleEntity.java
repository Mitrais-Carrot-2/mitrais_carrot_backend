package com.team.two.mitrais_carrot.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_roles")
public class UserRoleEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<UserEntity> user_id = new HashSet<>();

    @Column(name = "role_id")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<RoleEntity> role_id = new HashSet<>();
}
