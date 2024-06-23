package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Course;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Enrollment;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<List<Enrollment>> findByUserId(Long userId);
    Optional<List<Enrollment>> findByCourse(Course course);

    @Query("SELECT e.user FROM Enrollment e WHERE e.course.id = :courseId")
    List<User> findAllUsersByCourseId(@Param("courseId") Long courseId);
}