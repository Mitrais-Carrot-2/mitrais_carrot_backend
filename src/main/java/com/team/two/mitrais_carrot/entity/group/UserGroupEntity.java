package com.team.two.mitrais_carrot.entity.group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import lombok.*;
import javax.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer"})

@Entity(name = "UserGroupEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userGroups")
public class UserGroupEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "idUser")
//    private long idUser;

    //will not included
//    @Column(name = "group_id")
//    private int groupId;

    //newly added
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
//    private List<UserEntity> user;
}
