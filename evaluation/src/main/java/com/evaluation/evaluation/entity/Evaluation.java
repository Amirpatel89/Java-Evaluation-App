package com.evaluation.evaluation.entity;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Data
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String testeeId;

    @ElementCollection
    @CollectionTable(name = "subject_scores", joinColumns = @JoinColumn(name = "evaluation_id"))
    private List<SubjectScore> subjects;

}


