package com.cisco.app.entity;
/*
 * @author nbtwszol
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_note")
@Getter
@Setter
@NoArgsConstructor
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "case_id")
    private CaseEntity caseEntity;
    private String details;
}
