package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizActivityLogResponse {
    Long logId;
    Long userId;
    Long quizId;
    private double grade;
    private Long difficultyLevel;
}
