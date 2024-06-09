package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.controller;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.EnrollmentRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.EnrollmentResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Enrollment;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.services.UserDetailsImpl;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.EnrollmentService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollment")
@CrossOrigin
@RestController
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final UserService userService;

    @PostMapping("/{courseId}")
    public ResponseEntity<Long> enrollUserInCourse(@PathVariable Long courseId) {
        UserDetailsImpl user = userService.getUserDetails();
        return ResponseEntity.ok(enrollmentService.enrollUserInCourse(user.getId(), courseId));
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponse>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/user")
    public ResponseEntity<List<EnrollmentResponse>> getEnrollmentsByUser() {
        UserDetailsImpl user = userService.getUserDetails();
        return  ResponseEntity.ok(enrollmentService.getEnrollmentsByUser(user.getId()));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponse>> getEnrollmentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourse(courseId));
    }

    @PutMapping("/{enrollmentId}")
    public ResponseEntity<?> updateEnrollment(@PathVariable  Long enrollmentId, @RequestBody EnrollmentRequest updatedEnrollment) {
         enrollmentService.updateEnrollment(enrollmentId, updatedEnrollment);
         return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable Long enrollmentId) {
        enrollmentService.deleteEnrollment(enrollmentId);
        return  ResponseEntity.noContent().build();
    }
}