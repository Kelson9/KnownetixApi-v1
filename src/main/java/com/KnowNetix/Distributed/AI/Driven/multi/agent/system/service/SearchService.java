package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.CourseResponse;

import java.util.List;

public interface SearchService {
    List<CourseResponse> searchForCourse(String query);
}
