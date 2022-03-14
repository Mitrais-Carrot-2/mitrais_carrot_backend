package com.team.two.mitrais_carrot.entity.farmer;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
// import javax.validation.constraints.NotBlank;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "barns")
public class BarnEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// TODO : Buat relasi ke barnToFreezer sebagai PK
	private int id;

	@Column(name = "id_user")
	// TODO : Buat relasi ke user sebagai FK
	private Long idUser;

	@Column(name = "barn_name")
	private String barnName;

	@Column(name = "carrot_amount")
	private Long carrotAmount;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "distributed_carrot")
	@Value("${cp.barns.distributed_carrot: 0}")
	private Long distributedCarrot;
}
