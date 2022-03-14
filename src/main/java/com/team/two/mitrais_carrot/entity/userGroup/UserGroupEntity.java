package com.team.two.mitrais_carrot.entity.userGroup;

import lombok.*;
import javax.persistence.*;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.group.GroupEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userGroups")
public class UserGroupEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "group_id")
    private int groupId;
    //user_id disini tuh manager atau siapa yang ngemanage group nya
//    @Column(name = "user_id")
//    @OneToMany(mappedBy = "userGroups")
//    @JoinTable(name = "users", joinColumns = @JoinColumn(name = "userGroup_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private UserEntity user_id;

//    @Column(name = "group_id")
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "groups", joinColumns = @JoinColumn(name = "userGroup_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
//    private GroupEntity group_id;
}
