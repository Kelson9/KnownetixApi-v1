package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.controller;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.CourseResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Course;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.CourseService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
@CrossOrigin
public class SearchController {

    private final SearchService  searchService;

    @GetMapping
    public ResponseEntity<List<CourseResponse>> searchCourses(@RequestParam(required = false) String query) {
        return  ResponseEntity.ok(searchService.searchForCourse(query));
    }
}