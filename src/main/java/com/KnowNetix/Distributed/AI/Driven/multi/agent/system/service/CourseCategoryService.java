package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.CourseRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.CourseResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.CourseCategory;

import java.util.List;

public interface CourseCategoryService {

    Long createCourseCategory(CourseCategory courseCategory, Long userId);

    List<CourseCategory> getCourseCategories();

    CourseCategory getCourseCategory(Long courseCategoryId);

    void updateCourseCategory(Long courseCategoryId, CourseCategory courseCategory);

    void deleteCourseCategory(Long courseCategoryId);
}
