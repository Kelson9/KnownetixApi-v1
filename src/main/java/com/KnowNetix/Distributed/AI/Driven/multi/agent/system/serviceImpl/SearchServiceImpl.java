package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.CourseResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Course;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.CourseRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.ModuleService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final CourseRepository courseRepository;
    private final ModuleService moduleService;

    @Override
    public List<CourseResponse> searchForCourse(String query) {
        List<Course> courses = courseRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrInstructorContainingIgnoreCase(query, query, query);
        List<CourseResponse> courseResponses = new ArrayList<>();

        for (Course course : courses) {
            CourseResponse courseResponse = CourseResponse.builder()
                    .instructor(course.getInstructor())
                    .imageUrl(course.getImageUrl())
                    .duration(course.getDuration())
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .id(course.getId())
                    .moduleResponse(moduleService.getAllModulesForACourse(course.getId()))
                    .build();
            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }
}