package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.controller;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.QuizRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.QuizResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.services.UserDetailsImpl;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.QuizService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final UserService userService;

    @PostMapping("/{lessonId}")
    public ResponseEntity<Long> createQuiz(@RequestBody QuizRequest quizRequest, @PathVariable Long lessonId) {
        UserDetailsImpl userDetails = userService.getUserDetails();
        return ResponseEntity.ok(quizService.createQuiz(quizRequest, userDetails.getId(), lessonId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuizResponse>> getQuizzes() {
        return ResponseEntity.ok(quizService.getQuizzes());
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<QuizResponse>> getAllQuizzesForALesson(@PathVariable Long lessonId) {
        return ResponseEntity.ok(quizService.getAllQuizzesForALesson(lessonId));
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizResponse> getQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizService.getQuiz(quizId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<QuizResponse>> getUserCreatedQuiz() {
        UserDetailsImpl userDetails = userService.getUserDetails();
        return ResponseEntity.ok(quizService.getUserCreatedQuizzes(userDetails.getId()));
    }

    @PutMapping("/update/{quizId}")
    public ResponseEntity<?> updateQuiz(@RequestBody QuizRequest quizRequest, @PathVariable Long quizId) {
        quizService.updateQuiz(quizId, quizRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.noContent().build();
    }
}