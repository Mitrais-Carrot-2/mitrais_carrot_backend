package com.team.two.mitrais_carrot.entity.merchant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer"})

@Entity(name = "BazaarItemEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bazaarItems")
public class BazaarItemEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private long price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "image")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;

//    @Column(name = "expiryDate")
//    private LocalDate expiryDate;

//    @Column(name = "bazaar_id")
//    private Long bazaarId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bazaar_id")
    private BazaarEntity bazaar_id;
}