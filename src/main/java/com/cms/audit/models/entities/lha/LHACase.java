package com.cms.audit.models.entities.lha;

import java.util.Set;

import com.cms.audit.models.entities.schedule.Schedule;

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
@Table(name = "lha_case")
public class LHACase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "lhacase")
    private Set<LHACategoriry> lhaCategory;

}
