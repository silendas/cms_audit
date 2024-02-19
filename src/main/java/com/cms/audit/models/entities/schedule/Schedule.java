// package com.cms.audit.models.entities.schedule;

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
// @Table( name = "t_checklist")
// public class Schedule {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @OneToMany(mappedBy = "user")
//     @Column(name = "user_id")
//     private Long userId;

//     @OneToMany(mappedBy = "branch")
//     @Column(name = "branch_id")
//     private Long branchId;

//     private Date planning_start;

//     private Date planning_end;

// }
