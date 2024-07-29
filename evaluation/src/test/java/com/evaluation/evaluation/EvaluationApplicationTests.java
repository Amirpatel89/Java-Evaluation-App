package com.evaluation.evaluation;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.evaluation.evaluation.entity.Evaluation;
import com.evaluation.evaluation.service.EvaluationService;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class EvaluationApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EvaluationService evaluationService;

    @Test
    void saveEvaluationSheetsTest() throws Exception {
        String evaluationJson = "[{\"testeeId\":\"342\",\"subjects\":[{\"subject\":\"maths\",\"totalQuestions\":100,\"correct\":72,\"incorrect\":15}]}]";
        mockMvc.perform(post("/evaluation/sheets")
                .content(evaluationJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    void getEvaluationScoresTest() throws Exception {
        Evaluation evaluation = new Evaluation();
        evaluation.setTesteeId("342");
        evaluation.setSubjects(Collections.emptyList());
        evaluationService.saveEvaluation(evaluation);

        mockMvc.perform(get("/evaluation/scores"))
                .andExpect(status().isOk());
    }
}
