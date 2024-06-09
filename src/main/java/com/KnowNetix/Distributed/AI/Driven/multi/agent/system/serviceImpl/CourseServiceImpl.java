package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.CourseRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.CourseResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.exception.ResourceNotFoundException;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Course;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.CourseCategory;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Level;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.User;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.CourseCategoryRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.CourseRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.UserRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.CourseService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private  final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ModuleService moduleService;

    private final CourseCategoryRepository courseCategoryRepository;

    @Override
    public Long createCourse(CourseRequest courseRequest, Long userId, Long categoryId, Level level) {
        Optional<User> userOptional = userRepository.findById(userId);

        if(!userOptional.isPresent()){
            throw new ResourceNotFoundException("User not found");
        }

        Optional<CourseCategory> courseCategory = courseCategoryRepository.findById(categoryId);

        if(!courseCategory.isPresent()){
            throw new ResourceNotFoundException("Course Category not found not found");
        }

        Course course = Course.builder()
                .title(courseRequest.getTitle())
                .description(courseRequest.getDescription())
                .duration(courseRequest.getDuration())
                .imageUrl(courseRequest.getImageUrl())
                .courseLevel(level)
                .instructor(courseRequest.getInstructor())
                .courseCategory(courseCategory.get())
                .createdBy(userOptional.get())
                .build();

       Course savedCourse = courseRepository.save(course);
        return savedCourse.getId();
    }

    @Override
    public List<CourseResponse> getCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();

        for(Course course: courses) {

            CourseResponse courseResponse = CourseResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .imageUrl(course.getImageUrl())
                    .duration(course.getDuration())
                    .courseLevel(course.getCourseLevel())
                    .instructor(course.getInstructor())
                    .moduleResponse(moduleService.getAllModulesForACourse(course.getId()))
                    .build();

            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }

    @Override
    public CourseResponse getCourse(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if(!courseOptional.isPresent()){
            throw new RuntimeException("CourseId  not found");
        }

        CourseResponse courseResponse = CourseResponse.builder()
                .id(courseOptional.get().getId())
                .title(courseOptional.get().getTitle())
                .duration(courseOptional.get().getDuration())
                .description(courseOptional.get().getDescription())
                .imageUrl(courseOptional.get().getImageUrl())
                .courseLevel(courseOptional.get().getCourseLevel())
                .moduleResponse(moduleService.getAllModulesForACourse(courseId))
                .instructor(courseOptional.get().getInstructor())
                .build();

        return courseResponse;
    }

    @Override
    public List<CourseResponse> getUserCreatedCourses(Long userId) {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();

        for(Course course: courses) {

            CourseResponse courseResponse = CourseResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .imageUrl(course.getImageUrl())
                    .duration(course.getDuration())
                    .courseLevel(course.getCourseLevel())
                    .moduleResponse(moduleService.getAllModulesForACourse(course.getId()))
                    .instructor(course.getInstructor())
                    .build();

            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }

    @Override
    public void updateCourse(Long courseId, CourseRequest courseRequest, Level level) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if(!courseOptional.isPresent()){
            throw new RuntimeException("CourseId  not found");
        }

        courseOptional.get().setDescription(courseRequest.getDescription());
        courseOptional.get().setDuration(courseRequest.getDuration());
        courseOptional.get().setImageUrl(courseRequest.getImageUrl());
        courseOptional.get().setTitle(courseRequest.getTitle());
        courseOptional.get().setCourseLevel(level);
        courseOptional.get().setInstructor(courseRequest.getInstructor());

        courseRepository.save(courseOptional.get());
    }

    @Override
    public void deleteCourse(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if(!courseOptional.isPresent()){
            throw new RuntimeException("CourseId  not found");
        }
        courseRepository.deleteById(courseId);
    }

    @Override
    public List<CourseResponse> getCoursesByCategory(String category) {
        List<Course> courses = courseRepository.findAllByCategory(category);

        List<CourseResponse> courseResponses = new ArrayList<>();

        for(Course course: courses) {

            CourseResponse courseResponse = CourseResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .imageUrl(course.getImageUrl())
                    .duration(course.getDuration())
                    .courseLevel(course.getCourseLevel())
                    .instructor(course.getInstructor())
                    .moduleResponse(moduleService.getAllModulesForACourse(course.getId()))
                    .build();

            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }

    @Override
    public List<CourseResponse> getUserRecommendedCourses(String category, Level level) {
        List<Course> courses = courseRepository.findAllByCategoryAndCourseLevel(category, level);

        List<CourseResponse> courseResponses = new ArrayList<>();

        for(Course course: courses) {

            CourseResponse courseResponse = CourseResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .imageUrl(course.getImageUrl())
                    .duration(course.getDuration())
                    .courseLevel(course.getCourseLevel())
                    .instructor(course.getInstructor())
                    .moduleResponse(moduleService.getAllModulesForACourse(course.getId()))
                    .build();

            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }
}