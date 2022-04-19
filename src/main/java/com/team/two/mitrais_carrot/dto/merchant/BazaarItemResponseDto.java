package com.team.two.mitrais_carrot.dto.merchant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Lob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BazaarItemResponseDto {
    private Integer id;
    private String name;
    private long price;
    private int quantity;
    private String description;
    private BazaarEntityDto bazaar;

    @AllArgsConstructor
    class BazaarEntityDto {
        public Integer id;
        public String bazaarName;
    }

    public BazaarItemResponseDto(BazaarItemEntity bazaarItemEntity){
        this.id = bazaarItemEntity.getId();
        this.name = bazaarItemEntity.getName();
        this.price = bazaarItemEntity.getPrice();
        this.quantity = bazaarItemEntity.getQuantity();
        this.description = bazaarItemEntity.getDescription();
        this.bazaar = new BazaarEntityDto(bazaarItemEntity.getBazaar().getId(), bazaarItemEntity.getBazaar().getBazaarName());
    }
}
