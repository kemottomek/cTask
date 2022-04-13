package com.cisco.app.entity;

import com.cisco.app.generated.model.ModelCase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/*
 * @author nbtwszol
 */
@Entity
@Table(name = "t_cases")
@Getter
@Setter
@NoArgsConstructor
public class CaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "severity")
    private Integer severity;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private ModelCase.StatusEnum status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    @OneToMany(mappedBy = "caseEntity", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<NoteEntity> notes = new ArrayList<>();

}
