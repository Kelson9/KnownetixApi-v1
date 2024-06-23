package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.controller;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.CourseActivityLogRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.ActivityLog;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.services.UserDetailsImpl;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/course-logs")
@CrossOrigin
@RestController
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    @GetMapping("/{logId}")
    public ResponseEntity<ActivityLog> getActivityLogById(@PathVariable Long logId){
        return ResponseEntity.ok(activityLogService.getActivityLogById(logId));
    }

    @GetMapping()
    public ResponseEntity<List<ActivityLog>> getAllActivityLogs(){
        return ResponseEntity.ok(activityLogService.getAllActivityLogs());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ActivityLog>> getActivityLogsByUser(@PathVariable Long userId){
        return ResponseEntity.ok(activityLogService.getActivityLogsByUser(userId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ActivityLog>> getActivityLogsByCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(activityLogService.getActivityLogsByCourse(courseId));
    }

    @DeleteMapping("/{logId}")
    public ResponseEntity<?> deleteActivityLog(Long logId){
        activityLogService.deleteActivityLog(logId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> SaveActivityLog(@RequestBody CourseActivityLogRequest courseActivityLogRequest){
        activityLogService.SaveActivityLog(courseActivityLogRequest);
        return ResponseEntity.noContent().build();
    }
}