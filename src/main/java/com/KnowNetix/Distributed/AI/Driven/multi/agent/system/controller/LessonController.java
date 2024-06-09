package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.controller;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.LessonRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.LessonResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.services.UserDetailsImpl;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.LessonService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/lessons")
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;
    private final UserService userService;

    @PostMapping("/{moduleId}")
    public ResponseEntity<Long> createLesson(@RequestBody LessonRequest lessonRequest, @PathVariable Long moduleId){
        UserDetailsImpl userDetails = userService.getUserDetails();
        return ResponseEntity.ok(lessonService.createLesson(lessonRequest, userDetails.getId(), moduleId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LessonResponse>> getLessons(){
        return ResponseEntity.ok(lessonService.getLessons());
    }

    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<LessonResponse>> getAllLessonsForAModule(@PathVariable Long moduleId){
        return ResponseEntity.ok(lessonService.getAllLessonsForAModule(moduleId));
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonResponse> getLesson(@PathVariable Long lessonId){
        return ResponseEntity.ok(lessonService.getLesson(lessonId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<LessonResponse>> getUserCreatedLesson(){
        UserDetailsImpl userDetails = userService.getUserDetails();
        return ResponseEntity.ok(lessonService.getUserCreatedLessons(userDetails.getId()));
    }

    @PutMapping("/update/{lessonId}")
    public ResponseEntity<?> updateLesson(@RequestBody LessonRequest lessonRequest, @PathVariable Long lessonId){
        lessonService.updateLesson(lessonId, lessonRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long lessonId) {
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.noContent().build();
    }
}
