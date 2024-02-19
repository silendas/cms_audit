package com.cms.audit.models.entities.schedule;

import java.util.Date;

import com.cms.audit.models.entities.branch.Branch;
import com.cms.audit.models.entities.schedule.Schedule_categories;
import com.cms.audit.models.entities.users.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Enumerated(EnumType.STRING)
    private Schedule_categories categories;

    private Date planning_start;

    private Date planning_end;

    private Date created_at;

}
