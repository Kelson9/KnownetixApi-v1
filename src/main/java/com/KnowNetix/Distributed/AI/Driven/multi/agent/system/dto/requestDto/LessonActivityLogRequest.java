package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonActivityLogRequest {
    private Long userId;
    private Long lessonId;
    private String timeSpent;
    private double grade;
}