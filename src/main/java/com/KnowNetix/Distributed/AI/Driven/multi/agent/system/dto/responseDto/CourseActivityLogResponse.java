package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseActivityLogResponse {
    private Long logId;
    private Long userId;
    private  Long courseId;
    private String timeSpent;
    private double grade;
}