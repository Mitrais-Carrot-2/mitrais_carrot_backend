package com.team.two.mitrais_carrot.entity.basket;

import lombok.*;

import javax.persistence.*;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userBaskets")
public class UserBasketEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

//    @JoinColumn(name = "user_id")
//    @OneToMany(mappedBy = "userBaskets")
//    @JoinTable(name = "users", joinColumns = @JoinColumn(name = "userBasket_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private int user_id;

//    @JoinColumn(name = "userBasket_id")
//    @OneToMany(mappedBy = "userBaskets")
//    @JoinTable(name = "baskets", joinColumns = @JoinColumn(name = "userBasket_id"), inverseJoinColumns = @JoinColumn(name = "basket_id"))
    private BasketEntity basket = new BasketEntity();
}