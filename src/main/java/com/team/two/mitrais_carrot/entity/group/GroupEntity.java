package com.team.two.mitrais_carrot.entity.group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer"})

@Entity(name = "GroupEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class GroupEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

//    @Column(name = "manager_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity managerId;

    @NotNull
    @Column(name = "allocation")
    private long allocation;

    @Column(name = "note")
    private String note;

    @JsonIgnore
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserGroupEntity> userGroups;
//    @ManyToOne
//    @JoinTable(name = "userGroups", joinColumns = @JoinColumn(name = "group_id"))
//    private UserGroupEntity userGroup;
}
