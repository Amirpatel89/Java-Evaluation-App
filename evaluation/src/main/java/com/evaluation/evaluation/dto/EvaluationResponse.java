package com.evaluation.evaluation.dto;

import java.util.Map;

import lombok.Data;

@Data
public class EvaluationResponse {

    private String testeeId;
    private Map<String, Double> scores;

    public EvaluationResponse(String testeeId, Map<String, Double> scores) {
        this.testeeId = testeeId;
        this.scores = scores;
    }


}
