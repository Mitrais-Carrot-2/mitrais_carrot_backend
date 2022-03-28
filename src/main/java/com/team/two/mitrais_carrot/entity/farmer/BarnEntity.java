package com.team.two.mitrais_carrot.entity.farmer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.team.two.mitrais_carrot.entity.admin.BarnRewardEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
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
	private Integer id;

	@Column(name = "user_id")
	private Long userId;

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
	private Long distributedCarrot = 0L;

	@OneToMany(mappedBy = "barn", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BasketEntity> baskets = new ArrayList<>();

	@OneToMany(mappedBy = "barn", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<FreezerEntity> freezers = new ArrayList<>();

	@OneToMany(mappedBy = "barn", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BarnRewardEntity> barnReward = new ArrayList<>();
	
}
