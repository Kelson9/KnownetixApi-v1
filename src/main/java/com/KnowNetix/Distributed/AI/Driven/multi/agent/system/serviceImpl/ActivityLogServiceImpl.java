package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.CourseActivityLogRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.exception.ErrorCodes;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.exception.ResourceNotFoundException;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.ActivityLog;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Course;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.ActivityLogRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.CourseRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.ActivityLogService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    private final CourseRepository courseRepository;

    private  final UserService userService;

    @Override
    public ActivityLog getActivityLogById(Long logId) {

        Optional<ActivityLog> activityLog = activityLogRepository.findById(logId);
        if(!activityLog.isPresent()){
            throw new ResourceNotFoundException(ErrorCodes.RESOURCE_NOT_FOUND.getMessage());
        }
        return activityLog.get();
    }

    @Override
    public List<ActivityLog> getAllActivityLogs() {
        return activityLogRepository.findAll();
    }

    @Override
    public List<ActivityLog> getActivityLogsByUser(Long userId) {
        return activityLogRepository.findAllByUserId(userId);
    }

    @Override
    public List<ActivityLog> getActivityLogsByCourse(Long courseId) {
        return activityLogRepository.findAllByCourseId(courseId);
    }

    @Override
    public void deleteActivityLog(Long logId) {
        ActivityLog activityLog  = getActivityLogById(logId);
        activityLogRepository.delete(activityLog);
    }

    @Override
    public void SaveActivityLog(CourseActivityLogRequest courseActivityLogRequest) {
        ActivityLog activityLog = new ActivityLog();
        activityLog.setLogId(courseActivityLogRequest.getCourseId());
        activityLog.setGrade(courseActivityLogRequest.getGrade());
        activityLog.setTimeSpent(courseActivityLogRequest.getTimeSpent());
        activityLog.setCourse(getACourse(courseActivityLogRequest.getCourseId()));
        activityLog.setUser(userService.getUser(courseActivityLogRequest.getUserId()));

        activityLogRepository.save(activityLog);
    }

    public Course getACourse(Long courseId){
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(!courseOptional.isPresent()){
            throw new ResourceNotFoundException(ErrorCodes.RESOURCE_NOT_FOUND.getMessage());
        }
        return courseOptional.get();
    }
}