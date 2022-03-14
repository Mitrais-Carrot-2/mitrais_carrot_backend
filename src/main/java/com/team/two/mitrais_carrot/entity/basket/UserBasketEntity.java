package com.team.two.mitrais_carrot.entity.basket;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_baskets")
public class UserBasketEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private int user_id;

    @Column(name ="basket_id")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "baskets", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "basket_id"))
    private int basket_id;
}