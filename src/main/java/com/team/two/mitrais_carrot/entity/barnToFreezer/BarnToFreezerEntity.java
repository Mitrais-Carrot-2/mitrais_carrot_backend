//package com.team.two.mitrais_carrot.entity.barnToFreezer;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinTable;
//import javax.persistence.OneToMany;
//import javax.persistence.JoinColumn;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//
//import com.team.two.mitrais_carrot.entity.auth.UserEntity;
//import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
//import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "barnToFreezer")
//public class BarnToFreezerEntity {
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name = "user_id")
//    @OneToMany(mappedBy = "barnToFreezer")
//    @JoinTable(name = "users", joinColumns = @JoinColumn(name = "barnToFreezer_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private UserEntity user_id;
//
//    @Column(name = "barn_id")
//    @OneToMany(mappedBy = "barnToFreezer")
//    @JoinTable(name = "barns", joinColumns = @JoinColumn(name = "barnToFreezer_id"), inverseJoinColumns = @JoinColumn(name = "barn_id"))
//    private BarnEntity barn_id;
//
//    @Column(name = "freezer_id")
//    @OneToMany(mappedBy = "barnToFreezer")
//    @JoinTable(name = "freezers", joinColumns = @JoinColumn(name = "barnToFreezer_id"), inverseJoinColumns = @JoinColumn(name = "freezer_id"))
//    private FreezerEntity freezer_id;
//
//    @Column(name = "type")
//    @NotBlank
//    private String type;
//}
