package com.evaluation.evaluation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluation.evaluation.entity.Evaluation;
import com.evaluation.evaluation.repository.EvaluationRepository;

@Service
public class EvaluationService {
	
    @Autowired
    private EvaluationRepository evaluationRepository;

    public void saveEvaluation(Evaluation evaluation) {
        evaluationRepository.save(evaluation);
    }

    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }
}
