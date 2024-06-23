package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.ActivityLog;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.LessonActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonActivityLogRepository extends JpaRepository<LessonActivityLog, Long> {

    List<LessonActivityLog> findAllByUserId(Long userId);

    List<LessonActivityLog> findAllByLessonId(Long courseId);

}
