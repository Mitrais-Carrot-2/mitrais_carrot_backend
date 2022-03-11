package com.team.two.mitrais_carrot.entity.farmer;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
// import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "barns")
public class BarnEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


	@Column(name = "barnName")
	// @NotBlank
	private String barnName;

	@Column(name = "carrotAmount")
	private Long carrotAmount;

	@Column(name = "startDate")
	private LocalDate startDate;

	@Column(name = "expiredDate")
	private LocalDate expiredDate;

	@Column(name = "isActive")
	private Boolean isActive;

	@Column(name = "distributedCarrot", columnDefinition = "0")
	private Long distributedCarrot;


}
