package com.team.two.mitrais_carrot.entity.transfer;


import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FreezerTransferHistory")
@ToString
public class FreezerTransferHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transferId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freezer")
    private FreezerEntity freezer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private UserEntity user;

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