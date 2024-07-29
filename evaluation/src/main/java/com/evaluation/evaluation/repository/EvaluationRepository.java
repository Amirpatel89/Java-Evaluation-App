package com.evaluation.evaluation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluation.evaluation.entity.Evaluation;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

}
