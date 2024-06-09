package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.ActivityLog;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.ActivityLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {


    @Override
    public ActivityLog getActivityLogById(Long logId) {
        return null;
    }

    @Override
    public List<ActivityLog> getAllActivityLogs() {
        return null;
    }

    @Override
    public List<ActivityLog> getActivityLogsByUser(Long userId) {
        return null;
    }

    @Override
    public List<ActivityLog> getActivityLogsByCourse(Long courseId) {
        return null;
    }

    @Override
    public void deleteActivityLog(Long logId) {

    }
}
