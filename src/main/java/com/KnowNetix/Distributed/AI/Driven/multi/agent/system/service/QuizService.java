package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.QuizRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.QuizResponse;

import java.util.List;

public interface QuizService {

    Long createQuiz(QuizRequest quizRequest, Long userId, Long lessonId);

    List<QuizResponse> getQuizzes();

    List<QuizResponse> getAllQuizzesForALesson(Long lessonId);

    QuizResponse getQuiz(Long quizId);

    List<QuizResponse> getUserCreatedQuizzes(Long userId);

    void updateQuiz(Long quizId, QuizRequest quizRequest);

    void deleteQuiz(Long quizId);
}