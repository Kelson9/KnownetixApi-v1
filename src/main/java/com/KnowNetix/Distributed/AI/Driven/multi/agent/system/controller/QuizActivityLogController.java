package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.controller;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.QuizActivityLogRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.QuizActivityLog;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.QuizActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz-logs")
@CrossOrigin
@RestController
public class QuizActivityLogController {

    private final QuizActivityLogService quizActivityLogService;

    @GetMapping("/{logId}")
    public ResponseEntity<QuizActivityLog> getQuizActivityLogById(@PathVariable Long logId){
        return ResponseEntity.ok(quizActivityLogService.getQuizActivityLogById(logId));
    }

    @GetMapping()
    public ResponseEntity<List<QuizActivityLog>> getAllQuizLogs(){
        return ResponseEntity.ok(quizActivityLogService.getAllQuizActivityLogs());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<QuizActivityLog>> getQuizLogsByUser(@PathVariable Long userId){
        return ResponseEntity.ok(quizActivityLogService.getQuizActivityLogsByUser(userId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<QuizActivityLog>> getQuizLogsByLesson(@PathVariable Long quizId){
        return ResponseEntity.ok(quizActivityLogService.getQuizActivityLogsByLesson(quizId));
    }

    @DeleteMapping("/{logId}")
    public ResponseEntity<?> deleteQuizLog(Long logId){
        quizActivityLogService.deleteQuizActivityLog(logId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> SaveQuizLog(@RequestBody QuizActivityLogRequest quizActivityLogRequest){
        quizActivityLogService.SaveQuizActivityLog(quizActivityLogRequest);
        return ResponseEntity.noContent().build();
    }
}