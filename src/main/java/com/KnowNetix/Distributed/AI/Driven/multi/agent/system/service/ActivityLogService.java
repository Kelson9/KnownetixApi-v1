package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.CourseActivityLogRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.ActivityLog;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.LessonActivityLog;

import java.util.List;

public interface ActivityLogService {

    ActivityLog getActivityLogById(Long logId);
    List<ActivityLog> getAllActivityLogs();
    List<ActivityLog> getActivityLogsByUser(Long userId);
    List<ActivityLog> getActivityLogsByCourse(Long courseId);
    void deleteActivityLog(Long logId);
    void SaveActivityLog(CourseActivityLogRequest courseActivityLogRequest);
}
