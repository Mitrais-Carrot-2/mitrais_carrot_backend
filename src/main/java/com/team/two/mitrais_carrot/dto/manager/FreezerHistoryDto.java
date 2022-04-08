package com.team.two.mitrais_carrot.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreezerHistoryDto {
    private String name;
    private String jf;
    private String grade;
    private Long carrot;
    private String note;
    private LocalDateTime date;
}
