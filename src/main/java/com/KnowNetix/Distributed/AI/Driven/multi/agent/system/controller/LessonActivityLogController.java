package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.controller;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.LessonActivityLogRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.LessonActivityLog;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.LessonActivityLogService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/lesson-logs")
@CrossOrigin
@RestController
public class LessonActivityLogController {

    private final LessonActivityLogService lessonActivityLogService;

    @GetMapping("/{logId}")
    public ResponseEntity<LessonActivityLog> getLessonActivityLogById(@PathVariable Long logId){
        return ResponseEntity.ok(lessonActivityLogService.getLessonActivityLogById(logId));
    }

    @GetMapping()
    public ResponseEntity<List<LessonActivityLog>> getAllLessonsLogs(){
        return ResponseEntity.ok(lessonActivityLogService.getAllLessonActivityLogs());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LessonActivityLog>> getLessonLogsByUser(@PathVariable Long userId){
        return ResponseEntity.ok(lessonActivityLogService.getLessonActivityLogsByUser(userId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<LessonActivityLog>> getLessonLogsByCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(lessonActivityLogService.getLessonActivityLogsByCourse(courseId));
    }

    @DeleteMapping("/{logId}")
    public ResponseEntity<?> deleteLessonLog(Long logId){
        lessonActivityLogService.deleteLessonActivityLog(logId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> SaveLessonLog(@RequestBody LessonActivityLogRequest lessonActivityLogRequest){
        lessonActivityLogService.SaveLessonActivityLog(lessonActivityLogRequest);
        return ResponseEntity.noContent().build();
    }

}
