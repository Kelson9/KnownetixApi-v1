package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.AssessmentRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.AssessmentResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.AssessmentRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.AssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;

    @Override
    public void saveAssessment(AssessmentRequest assessmentRequest) {

    }

    @Override
    public List<AssessmentResponse> getAllUserAssessments(Long userId) {
        return null;
    }
}
