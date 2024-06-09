package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.CourseRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.CourseResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.CourseCategory;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Level;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

        Long createCourse(CourseRequest courseRequest, Long userId, Long categoryId, Level level);

        List<CourseResponse> getCourses();

        CourseResponse getCourse(Long courseId);

        List<CourseResponse> getUserCreatedCourses(Long userId);

        void updateCourse(Long courseId, CourseRequest courseRequest, Level level);

        void deleteCourse(Long courseId);


        List<CourseResponse> getCoursesByCategory(String category);

        List<CourseResponse> getUserRecommendedCourses(String category, Level level);

}
