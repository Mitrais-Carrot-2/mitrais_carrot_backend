package com.team.two.mitrais_carrot.dto.user;

import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class StaffDto {
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String jobFamily;
    private String jobGrade;
    private String office;
    private BasketEntity basket;

    public StaffDto(Long userId, String username, String firstName, String lastName, String jobFamily, String jobGrade, String office, BasketEntity basket) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobFamily = jobFamily;
        this.jobGrade = jobGrade;
        this.office = office;
        this.basket = basket;
    }

    public StaffDto(Long userId, String username, String firstName, String lastName, String jobFamily, String jobGrade, String office) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobFamily = jobFamily;
        this.jobGrade = jobGrade;
        this.office = office;
    }
}
