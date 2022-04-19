package com.team.two.mitrais_carrot.dto.merchant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team.two.mitrais_carrot.entity.merchant.BazaarEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BazaarResponseDto {
    private Integer id;
    private String bazaarName;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<BazaarItemEntityDto> items = new ArrayList<>();

    class BazaarItemEntityDto {
        public Integer id;
        public String name;
        public long price;
        public int quantity;
        public String description;

        public BazaarItemEntityDto (BazaarItemEntity bazaarItemEntity){
            this.id = bazaarItemEntity.getId();
            this.name = bazaarItemEntity.getName();
            this.price = bazaarItemEntity.getPrice();
            this.quantity = bazaarItemEntity.getQuantity();
            this.description = bazaarItemEntity.getDescription();
        }
    }

    public BazaarResponseDto(BazaarEntity bazaarEntity){
        this.id = bazaarEntity.getId();
        this.bazaarName = bazaarEntity.getBazaarName();
        this.startDate = bazaarEntity.getStartDate();
        this.endDate = bazaarEntity.getEndDate();
        this.items = bazaarEntity.getItems().stream()
                .map(itemEntity -> new BazaarItemEntityDto(itemEntity))
                .collect(Collectors.toList());
    }

}
