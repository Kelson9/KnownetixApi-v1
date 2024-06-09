package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.controller;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.CourseCategory;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.services.UserDetailsImpl;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.CourseCategoryService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/course_categories")
@CrossOrigin
@RestController
public class CourseCategoryController {

    private final CourseCategoryService courseCategoryService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<Long> createCourseCategory(@RequestBody CourseCategory courseCategory) {

        UserDetailsImpl userDetails = userService.getUserDetails();
        return ResponseEntity.ok(courseCategoryService.createCourseCategory(courseCategory, userDetails.getId()));
    }

    @GetMapping
    public ResponseEntity<List<CourseCategory>> getCourseCategories(){
        return ResponseEntity.ok(courseCategoryService.getCourseCategories());
    }

    @GetMapping("/{courseCategoryId}")
    public ResponseEntity<CourseCategory> getCourseCategory(Long courseCategoryId){
        return ResponseEntity.ok(courseCategoryService.getCourseCategory(courseCategoryId));
    }


    @PutMapping("/{courseCategoryId}")
    public ResponseEntity<?>updateCourseCategory(@PathVariable Long courseCategoryId, @RequestBody CourseCategory courseCategory) {
        courseCategoryService.updateCourseCategory(courseCategoryId, courseCategory);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{courseCategoryId}")
    public ResponseEntity<?> deleteCourseCategory(Long courseCategoryId){
        courseCategoryService.deleteCourseCategory(courseCategoryId);
        return ResponseEntity.noContent().build();
    }
}