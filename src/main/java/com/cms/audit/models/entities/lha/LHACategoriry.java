package com.cms.audit.models.entities.lha;

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
@Table(name = "lha_category")
public class LHACategoriry {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lha_case_id", nullable = false)
    private LHACase lhacase;

    private String name;

}
