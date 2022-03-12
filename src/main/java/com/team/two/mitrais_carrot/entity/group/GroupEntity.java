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

    //user_id disini tuh manager atau siapa yang ngemanage group nya
//    @Column(name = "user_id")
//    @OneToOne
//    @JoinTable(name = "users", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private UserEntity user_id;
}
