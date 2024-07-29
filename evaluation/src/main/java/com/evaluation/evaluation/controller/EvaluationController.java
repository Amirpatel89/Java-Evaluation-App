package com.evaluation.evaluation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.evaluation.evaluation.dto.EvaluationResponse;
import com.evaluation.evaluation.entity.Evaluation;
import com.evaluation.evaluation.entity.SubjectScore;
import com.evaluation.evaluation.service.EvaluationService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/sheets")
    public ResponseEntity<?> saveEvaluationSheets(@RequestBody List<Evaluation> evaluations) {
        for (Evaluation evaluation : evaluations) {
            evaluationService.saveEvaluation(evaluation);
        }
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/scores")
    public ResponseEntity<?> getEvaluationScores(
            @RequestParam(required = false) List<String> testeeIds,
            @RequestParam(required = false) List<String> subjects) {
        List<Evaluation> evaluations = evaluationService.getAllEvaluations();

        if (testeeIds != null) {
            evaluations = evaluations.stream()
                    .filter(e -> testeeIds.contains(e.getTesteeId()))
                    .collect(Collectors.toList());
        }

        List<EvaluationResponse> response = evaluations.stream()
                .map(evaluation -> {
                    Map<String, Double> scores = evaluation.getSubjects().stream()
                            .filter(subjectScore -> subjects == null || subjects.contains(subjectScore.getSubject()))
                            .collect(Collectors.toMap(
                                    SubjectScore::getSubject,
                                    this::calculateScore
                            ));

                    // Calculate total and average
                    double total = scores.values().stream().mapToDouble(Double::doubleValue).sum();
                    double average = scores.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                    scores.put("total", total);
                    scores.put("average", average);

                    return new EvaluationResponse(evaluation.getTesteeId(), scores);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    private double calculateScore(SubjectScore subjectScore) {
        return subjectScore.getCorrect() - 0.25 * subjectScore.getIncorrect();
    }
}
