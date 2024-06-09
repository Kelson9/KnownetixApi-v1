package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.EnrollmentRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.EnrollmentResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Enrollment;

import java.util.List;

public interface EnrollmentService {

    Long enrollUserInCourse(Long userId, Long courseId);
    List<EnrollmentResponse> getAllEnrollments();
    List<EnrollmentResponse> getEnrollmentsByUser(Long userId);
    List<EnrollmentResponse> getEnrollmentsByCourse(Long courseId);
    void updateEnrollment(Long enrollmentId, EnrollmentRequest updatedEnrollment);
    void deleteEnrollment(Long enrollmentId);
}
