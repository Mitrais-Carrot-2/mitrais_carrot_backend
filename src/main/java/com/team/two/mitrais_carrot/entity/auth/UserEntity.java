package com.team.two.mitrais_carrot.entity.auth;

import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.group.UserGroupEntity;
import lombok.*;

import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
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
//    @NotBlank(message = "Username is required")
    private String username;

    @Column(name = "password", nullable = false)
//    @NotBlank(message = "Password is required")
    private String password;

    @Column(name = "email", nullable = false)
//    @Email(message = "Please provide a valid email")
//    @NotBlank(message = "Email is required")
    private String email;

//    @Column(name = "firstName", nullable = false)
//    @NotBlank(message = "First name is required")
    private String firstName;

//    @Column(name = "lastName", nullable = false)
//    @NotBlank(message = "Last name is required")
    private String lastName;

//    @Column(name = "address", nullable = false)
//    @NotBlank(message = "Address is required")
    private String address;

   @Column(name = "birthDate", nullable = false)
//    @NotBlank(message = "Birthdate is required")
    private LocalDate birthDate;

//    @Column(name = "supervisorId", nullable = false)
//    @NotBlank(message = "Supervisor ID is required")
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
    private byte[] image;

    @Column(name = "image_type")
    private String imageType;

    @Column(name = "image_size")
    private long imageSize;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BasketEntity> baskets = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserGroupEntity> userGroups;

    @Column(name = "dayOfYearBirthDay")
    private int dayOfYearBirthDay;
}
