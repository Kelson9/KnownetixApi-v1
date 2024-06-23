package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonActivityLogResponse {
    private Long logId;
    private Long userId;
    private Long lessonId;
    private String timeSpent;
    private double grade;
}
