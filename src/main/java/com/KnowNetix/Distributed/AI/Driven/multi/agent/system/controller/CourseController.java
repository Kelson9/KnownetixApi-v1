package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.controller;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.CourseRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.CourseResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Level;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.services.UserDetailsImpl;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.CourseService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
@CrossOrigin
@RestController
public class CourseController {

    private final CourseService courseService;
    private final UserService userService;

    @PostMapping("/{categoryId}")
    public ResponseEntity<Long> createCourse(@RequestBody CourseRequest courseRequest, @PathVariable Long categoryId, @RequestParam Level level){

        UserDetailsImpl userDetails = userService.getUserDetails();
        return ResponseEntity.ok(courseService.createCourse(courseRequest, userDetails.getId(), categoryId, level));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getCourses(){
        return ResponseEntity.ok(courseService.getCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.getCourse(courseId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<CourseResponse>> getUserCreatedCourses(){
        UserDetailsImpl userDetails = userService.getUserDetails();

        return ResponseEntity.ok(courseService.getUserCreatedCourses(userDetails.getId()));
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<?> updateCourse(@RequestBody CourseRequest courseRequest, @PathVariable Long courseId, @RequestParam Level level){
        courseService.updateCourse(courseId, courseRequest, level);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId){
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category")
    public ResponseEntity<List<CourseResponse>> getCoursesByCategory(@RequestBody String category) {
        return ResponseEntity.ok(courseService.getCoursesByCategory(category));
    }


    @GetMapping("/category/level")
    public ResponseEntity<List<CourseResponse>> getUserRecommendedCourses(@RequestParam String category, @RequestParam Level level) {
        return ResponseEntity.ok(courseService.getUserRecommendedCourses(category, level));
    }
}