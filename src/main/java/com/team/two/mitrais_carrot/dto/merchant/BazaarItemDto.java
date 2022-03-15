package com.team.two.mitrais_carrot.dto.merchant;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BazaarItemDto{
    private String name;
    private int price;
    private String description;
    private int quantity;
//    private byte[] image;
    //private Long bazaar_id;
}