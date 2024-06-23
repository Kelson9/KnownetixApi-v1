package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.QuizActivityLogRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.LessonActivityLog;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.QuizActivityLog;

import java.util.List;

public interface QuizActivityLogService {

    QuizActivityLog getQuizActivityLogById(Long logId);
    List<QuizActivityLog> getAllQuizActivityLogs();
    List<QuizActivityLog> getQuizActivityLogsByUser(Long userId);
    List<QuizActivityLog> getQuizActivityLogsByLesson(Long lessonId);
    void deleteQuizActivityLog(Long logId);
    void SaveQuizActivityLog(QuizActivityLogRequest quizActivityLogRequest);
}
