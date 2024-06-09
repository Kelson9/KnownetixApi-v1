package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnrollmentRequest {
    private Long enrollmentId;
    private LocalDateTime enrollmentDate;
    private String status;
    private Double progress;
}
