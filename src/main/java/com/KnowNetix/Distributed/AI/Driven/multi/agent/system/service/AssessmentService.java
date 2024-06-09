package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.AssessmentRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.AssessmentResponse;

import java.util.List;

public interface AssessmentService {

    void saveAssessment(AssessmentRequest assessmentRequest);
    List<AssessmentResponse> getAllUserAssessments(Long userId);
}
