package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.ActivityLog;

import java.util.List;

public interface ActivityLogService {

    ActivityLog getActivityLogById(Long logId);
    List<ActivityLog> getAllActivityLogs();
    List<ActivityLog> getActivityLogsByUser(Long userId);
    List<ActivityLog> getActivityLogsByCourse(Long courseId);
    void deleteActivityLog(Long logId);
}
