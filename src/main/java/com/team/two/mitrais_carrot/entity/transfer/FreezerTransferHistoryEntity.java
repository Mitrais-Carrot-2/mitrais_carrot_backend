package com.team.two.mitrais_carrot.entity.transfer;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FreezerTransferHistory")
@ToString
public class FreezerTransferHistoryEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE)
    private UUID transferId = UUID.randomUUID();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freezer")
    private FreezerEntity freezer;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private UserEntity receiverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private UserEntity managerId;

    @Column(name = "shareAt")
    private LocalDateTime shareAt;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ETransferType type;

    @Column(name = "note")
    private String note;

    @Column(name = "carrot_amount")
    private Long carrotAmount;
}