// package com.cms.audit.models.entities.lha;

// import java.util.Date;

// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// @Entity
// @Table( name = "t_lha")
// public class LHA {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(name = "created_at")
//     private Date createDate;
    
//     @OneToMany(mappedBy = "user")
//     private Long userId;

//     @OneToMany(mappedBy = "case")
//     private String caseId;    

// }
