package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizActivityLogRequest {

    Long userId;
    Long quizId;
    private double grade;
    private Long difficultyLevel;
}