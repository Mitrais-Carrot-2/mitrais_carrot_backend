package com.team.two.mitrais_carrot.entity.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})

@Entity(name = "GroupEntity")
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
    private Long managerId;

    @Column(name = "allocation")
    private long allocation;

    @Column(name = "note")
    private String note;

//    @ManyToOne
//    @JoinTable(name = "userGroups", joinColumns = @JoinColumn(name = "group_id"))
//    private UserGroupEntity userGroup;
}
