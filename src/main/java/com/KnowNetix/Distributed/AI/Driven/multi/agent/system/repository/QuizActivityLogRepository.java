package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.LessonActivityLog;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.QuizActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizActivityLogRepository extends JpaRepository<QuizActivityLog,Long> {
    List<QuizActivityLog> findAllByUserId(Long userId);

    List<QuizActivityLog> findAllByQuizId(Long quizId);
}
