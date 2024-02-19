package com.cms.audit.models.entities.lha;

import java.util.Date;
import java.util.Set;

import com.cms.audit.models.entities.users.User;
import com.cms.audit.models.entities.users.UserProfile;

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
@Table(name = "lha")
public class LHA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lha_case_id")
    private LHACase lhaCase;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lha_category_id")
    private LHACategoriry lhaCategory;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    private String status;

}
