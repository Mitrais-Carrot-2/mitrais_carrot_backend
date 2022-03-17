package com.team.two.mitrais_carrot.repository.farmer;

import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferToManagerRepository extends JpaRepository<FreezerEntity, Integer> {
}
