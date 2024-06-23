package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.LessonActivityLogRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.exception.ErrorCodes;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.exception.ResourceNotFoundException;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Lesson;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.LessonActivityLog;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.LessonActivityLogRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.LessonRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.LessonActivityLogService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonActivityLogServiceImpl implements LessonActivityLogService {

    private final LessonActivityLogRepository lessonActivityLogRepository;

    private final UserService userService;

    private final LessonRepository lessonRepository;


    @Override
    public LessonActivityLog getLessonActivityLogById(Long logId) {

        Optional<LessonActivityLog> lessonActivityLog = lessonActivityLogRepository.findById(logId);
        if(!lessonActivityLog.isPresent()){
            throw new ResourceNotFoundException(ErrorCodes.RESOURCE_NOT_FOUND.getMessage());
        }
        return lessonActivityLog.get();
    }

    @Override
    public List<LessonActivityLog> getAllLessonActivityLogs() {
        return lessonActivityLogRepository.findAll();
    }

    @Override
    public List<LessonActivityLog> getLessonActivityLogsByUser(Long userId) {

        return lessonActivityLogRepository.findAllByUserId(userId);
    }

    @Override
    public List<LessonActivityLog> getLessonActivityLogsByCourse(Long courseId) {

        return lessonActivityLogRepository.findAllByLessonId(courseId);
    }

    @Override
    public void deleteLessonActivityLog(Long logId) {
        LessonActivityLog activityLog  = getLessonActivityLogById(logId);
        lessonActivityLogRepository.delete(activityLog);
    }

    @Override
    public void SaveLessonActivityLog(LessonActivityLogRequest lessonActivityLogRequest) {
        LessonActivityLog activityLog = new LessonActivityLog();
        activityLog.setGrade(lessonActivityLogRequest.getGrade());
        activityLog.setLogId(lessonActivityLogRequest.getLessonId());
        activityLog.setTimeSpent(lessonActivityLogRequest.getTimeSpent());

        activityLog.setGrade(lessonActivityLogRequest.getGrade());
        activityLog.setUser(userService.getUser(lessonActivityLogRequest.getUserId()));
        activityLog.setLesson(getALesson(lessonActivityLogRequest.getLessonId()));

        lessonActivityLogRepository.save(activityLog);
    }

    public Lesson getALesson(Long lessonId){
        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);
        if(!lessonOptional.isPresent()){
            throw new ResourceNotFoundException(ErrorCodes.RESOURCE_NOT_FOUND.getMessage());
        }
        return lessonOptional.get();
    }
}