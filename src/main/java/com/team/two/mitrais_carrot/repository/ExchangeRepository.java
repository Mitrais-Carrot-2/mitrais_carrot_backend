package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.exchange.ExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeEntity, Integer> {
}
