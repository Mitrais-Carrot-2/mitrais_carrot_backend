package com.team.two.mitrais_carrot.entity.merchant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "price")
    private long price;

    @NotNull
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description", length = 1024)
    private String description;

    @Lob
    @Column(name = "image")
    @Type(type = "org.hibernate.type.BinaryType")
    @JsonIgnore
    private byte[] image;

    @Column(name = "image_type")
    private String imageType;

    @Column(name = "image_size")
    private long imageSize;


//    @Column(name = "expiryDate")
//    private LocalDate expiryDate;

//    @Column(name = "bazaar_id")
//    private Long bazaarId;

//    @JsonIgnore
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bazaar_id")
    private BazaarEntity bazaar;
}