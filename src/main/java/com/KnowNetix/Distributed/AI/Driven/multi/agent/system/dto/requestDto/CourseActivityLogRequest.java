package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseActivityLogRequest {
    private Long userId;
    private  Long courseId;
    private String timeSpent;
    private double grade;
}
