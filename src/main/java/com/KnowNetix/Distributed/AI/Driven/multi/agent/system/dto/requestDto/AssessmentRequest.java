package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto;

import lombok.Data;

@Data
public class AssessmentRequest {
    private String name;

    private String description;

    private Long difficultyLevel;

    private String score;
}