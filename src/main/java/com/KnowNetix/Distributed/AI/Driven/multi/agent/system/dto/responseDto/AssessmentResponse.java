package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssessmentResponse {
    private Long id;

    private String name;

    private  String description;

    private Long difficultyLevel;

    private String score;
}
