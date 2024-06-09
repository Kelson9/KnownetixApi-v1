package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EnrollmentResponse {
    private Long enrollmentId;
    private LocalDateTime enrollmentDate;
    private String status;
    private Double progress;
    CourseResponse courseResponse;
}
