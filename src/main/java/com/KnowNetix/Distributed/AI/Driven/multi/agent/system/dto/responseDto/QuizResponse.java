package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Level;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizResponse {
    private Long id;

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
    Level quizLevel;
}