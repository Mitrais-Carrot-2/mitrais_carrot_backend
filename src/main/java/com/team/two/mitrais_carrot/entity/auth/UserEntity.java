package com.team.two.mitrais_carrot.entity.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import com.team.two.mitrais_carrot.entity.group.UserGroupEntity;
import com.team.two.mitrais_carrot.entity.transfer.FreezerTransferHistoryEntity;
import lombok.*;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class UserEntity {
    public UserEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "username", nullable = false)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "joinDate")
    private LocalDate joinDate;

    @Column(name = "gender")
    private String gender;

    @Column(name="enableNotif",nullable = false)
    private boolean enableNotif;

   @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;
    private Long supervisorId;

    @Column(name = "jobFamily")
    private String jobFamily;

    @Column(name = "jobGrade")
    private String jobGrade;

    @Column(name = "office")
    private String office;

    @Lob
    @Column(name = "image")
    @Type(type = "org.hibernate.type.BinaryType")
    @JsonIgnore
    private byte[] image;

    @Column(name = "image_type")
    private String imageType;

    @Column(name = "image_size")
    private long imageSize;

//    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    // @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BasketEntity> baskets = new ArrayList<>();

    // @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserGroupEntity> userGroups;

    @JsonIgnore
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BarnEntity> barn = new ArrayList<>();

    // @JsonIgnore
    // @OneToMany(mappedBy = "managerId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private List<FreezerEntity> freezer = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "receiverId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FreezerTransferHistoryEntity> freezerTransferHistories = new ArrayList<>();

    @Column(name = "dayOfYearBirthDay")
    private int dayOfYearBirthDay;
}
