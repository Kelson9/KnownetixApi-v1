package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Level;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class QuizRequest {

    private String questionText;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String answer;

    private boolean isSelected;

    private Long difficultyLevel;

    private String score;


    @Enumerated(EnumType.STRING)
    Level quizLevel;}
