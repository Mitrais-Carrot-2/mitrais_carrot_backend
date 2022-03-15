package com.team.two.mitrais_carrot.entity.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;

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
    private int id;

    @Column(name = "user_id")
    private int userId;

    //will not included
//    @Column(name = "group_id")
//    private int groupId;

    //newly added
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
