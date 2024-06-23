package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.LessonActivityLogRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.LessonActivityLog;

import java.util.List;

public interface LessonActivityLogService {

    LessonActivityLog getLessonActivityLogById(Long logId);
    List<LessonActivityLog> getAllLessonActivityLogs();
    List<LessonActivityLog> getLessonActivityLogsByUser(Long userId);
    List<LessonActivityLog> getLessonActivityLogsByCourse(Long courseId);
    void deleteLessonActivityLog(Long logId);
    void SaveLessonActivityLog(LessonActivityLogRequest lessonActivityLogRequest);
}
