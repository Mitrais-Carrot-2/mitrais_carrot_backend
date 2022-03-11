package com.team.two.mitrais_carrot.entity.freezerToBasket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.employee.BasketEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "freezerToBasket")
public class FreezerToBasket{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;
    
    @Column(name = "type")
    public String type;

    @Column(name = "user_id")
    @OneToMany(mappedBy = "freezerToBasket")
    @JoinTable(name = "users", joinColumns = @JoinColumn(name = "breezerToBasket_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private UserEntity user_id;
    
    @Column(name = "freezer_id")
    @OneToMany(mappedBy = "freezerToBasket")
    @JoinTable(name = "freezers", joinColumns = @JoinColumn(name = "freezerToBasket_id"), inverseJoinColumns = @JoinColumn(name = "freezer_id"))
    public FreezerEntity  freezer_id;

    @Column(name = "basket_id")
    @OneToMany(mappedBy = "freezerToBasket")
    @JoinTable(name = "baskets", joinColumns = @JoinColumn(name = "freezerToBasket_id"), inverseJoinColumns = @JoinColumn(name = "basket_id"))
    public BasketEntity basket_id;
}