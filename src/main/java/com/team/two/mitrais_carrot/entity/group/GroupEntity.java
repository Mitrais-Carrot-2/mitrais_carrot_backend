package com.team.two.mitrais_carrot.entity.group;

import lombok.*;
import javax.persistence.*;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
public class GroupEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "manager_id")
    private int managerId;

    @ManyToOne
    @JoinTable(name = "userGroups", joinColumns = @JoinColumn(name = "id"))
    private UserGroupEntity userGroup;
}
