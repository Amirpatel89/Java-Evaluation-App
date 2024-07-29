package com.evaluation.evaluation.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class SubjectScore {

    private String subject;
    private int totalQuestions;
    private int correct;
    private int incorrect;


}