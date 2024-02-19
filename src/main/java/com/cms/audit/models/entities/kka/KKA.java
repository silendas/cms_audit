package com.cms.audit.models.entities.kka;

import java.util.Set;

import com.cms.audit.models.entities.branch.Branch;
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
@Table(name = "t_kka")
public class KKA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String title;

    @OneToMany(mappedBy = "kka")
    private Set<SubKKA> subKka;

}
