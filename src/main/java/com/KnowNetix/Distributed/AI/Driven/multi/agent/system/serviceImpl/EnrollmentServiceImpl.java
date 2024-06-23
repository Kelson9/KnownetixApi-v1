package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.EnrollmentRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.EnrollmentResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Course;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Enrollment;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.User;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.CourseRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.EnrollmentRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.CourseService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.EnrollmentService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserService userService;
    private final CourseRepository courseRepository;

    private final CourseService courseService;

    @Override
    public Long enrollUserInCourse(Long userId, Long courseId) {
        User user = userService.getUser(userId);
        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(getCourse(courseId));
        enrollment.setEnrollmentDate(LocalDateTime.now());
        enrollment.setStatus("active");
        enrollment.setProgress(0.0);
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return savedEnrollment.getEnrollmentId();
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollments() {

        List<Enrollment> enrollments = enrollmentRepository.findAll();
        return getListOfEnrollments(enrollments);
    }

    @Override
    public List<EnrollmentResponse> getEnrollmentsByUser(Long userId) {
        User user = userService.getUser(userId);
        Optional<List<Enrollment>> enrollments = enrollmentRepository.findByUserId(user.getId());
        return getListOfEnrollments(enrollments.get());
    }

    @Override
    public List<EnrollmentResponse> getEnrollmentsByCourse(Long courseId) {
        Course course = getCourse(courseId);
        Optional<List<Enrollment>> enrollments = enrollmentRepository.findByCourse(course);
        return getListOfEnrollments(enrollments.get());
    }

    @Override
    public void updateEnrollment(Long enrollmentId, EnrollmentRequest updatedEnrollment) {
        Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(enrollmentId);

        if(!enrollmentOptional.isPresent()){
            throw new RuntimeException("EnrollmentId  not found");
        }

        enrollmentOptional.get().setProgress(updatedEnrollment.getProgress());
        enrollmentOptional.get().setStatus(updatedEnrollment.getStatus());
        enrollmentOptional.get().setEnrollmentDate(updatedEnrollment.getEnrollmentDate());

        enrollmentRepository.save(enrollmentOptional.get());
    }

    @Override
    public void deleteEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(
                () -> new RuntimeException("Enrollment not found"));
        enrollmentRepository.delete(enrollment);
    }

    @Override
    public List<User> getUsersByEnrollment(Long courseId) {
        return enrollmentRepository.findAllUsersByCourseId(courseId);
    }

    Course getCourse(Long courseId){
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(!courseOptional.isPresent()){
            throw new RuntimeException("Course not found");
        }
        return courseOptional.get();
    }

    private List<EnrollmentResponse> getListOfEnrollments(List<Enrollment> enrollments){
        List<EnrollmentResponse> enrollmentResponses = new ArrayList<>();

        for(Enrollment enrollment: enrollments) {

            EnrollmentResponse enrollmentResponse = EnrollmentResponse.builder()
                    .enrollmentId(enrollment.getEnrollmentId())
                    .enrollmentDate(enrollment.getEnrollmentDate())
                    .courseResponse(courseService.getCourse(enrollment.getCourse().getId()))
                    .status(enrollment.getStatus())
                    .progress(enrollment.getProgress())
                    .build();

            enrollmentResponses.add(enrollmentResponse);
        }
        return enrollmentResponses;
    }

//    private List<EnrollmentResponse> getAllEnrollmentsForACourse(Long enrollmentId) {
//        List<Enrollment> enrollments = enrollmentRepository.findAllByModuleId(moduleId);
//        return getListOfEnrollments(enrollments);
//    }
}