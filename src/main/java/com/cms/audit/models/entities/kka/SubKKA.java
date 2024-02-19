package com.cms.audit.models.entities.kka;

import java.util.HashSet;
import java.util.Set;

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
@Table(name = "t_sub_kka")
public class SubKKA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kka_id", nullable = false)
    private KKA kka;

    private String subTitle;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dues_cheklist", joinColumns = @JoinColumn(name = "dues_id"), inverseJoinColumns = @JoinColumn(name = "checklist_id"))
    private Set<Checklist> checklisDues = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "suitability_cheklist", joinColumns = @JoinColumn(name = "suitability_id"), inverseJoinColumns = @JoinColumn(name = "checklist_id"))
    private Set<Checklist> checklistsSuitable = new HashSet<>();

    @Column(columnDefinition = "TEXT")
    private String description;

}
