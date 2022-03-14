package com.team.two.mitrais_carrot.entity.auth;

import com.team.two.mitrais_carrot.entity.basket.UserBasketEntity;
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
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private boolean flag;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

//    @ManyToOne(cascade = CascadeType.ALL)
    private UserBasketEntity userBasket = new UserBasketEntity();

    public UserEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public boolean isFlag() {
        return flag;
    }

    // public void setFlag(boolean flag) {
    // this.flag = flag;
    // }

    // public Set<RoleEntity> getRoles() {
    // return roles;
    // }

    // public void setRoles(Set<RoleEntity> roles) {
    // this.roles = roles;
    // }
}
