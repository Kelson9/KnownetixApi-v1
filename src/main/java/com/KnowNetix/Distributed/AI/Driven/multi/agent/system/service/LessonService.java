package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.LessonRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.LessonResponse;

import java.util.List;

public interface LessonService {

    Long createLesson(LessonRequest lessonRequest, Long userId, Long moduleId);

    List<LessonResponse> getLessons();

    List<LessonResponse> getAllLessonsForAModule(Long moduleId);

    LessonResponse getLesson(Long lessonId);

    List<LessonResponse> getUserCreatedLessons(Long userId);

    void updateLesson(Long courseId, LessonRequest lessonRequest);

    void deleteLesson(Long lessonId);
}