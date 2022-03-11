package com.team.two.mitrais_carrot.entity.transfer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.transferHistory.transferHistoryEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transfers")
public class TransferEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    @NotBlank
    private String type;

    @Column(name = "user_id")
    @OneToMany(mappedBy = "transfers")
    @JoinTable(name = "users", joinColumns = @JoinColumn(name = "transfer_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private UserEntity user_id;

    @Column(name = "transferHistory_id")
    @OneToMany(mappedBy = "transfers")
    @JoinTable(name = "transferHistories", joinColumns = @JoinColumn(name = "transfer_id"), inverseJoinColumns = @JoinColumn(name = "transferHistory_id"))
    private transferHistoryEntity transferHistory_id;
}
