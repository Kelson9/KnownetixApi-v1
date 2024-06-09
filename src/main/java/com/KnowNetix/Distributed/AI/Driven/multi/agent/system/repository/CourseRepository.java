package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Course;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrInstructorContainingIgnoreCase(String title, String description, String instructor);
    List<Course> findAllByCategory(String category);
    List<Course> findAllByCategoryAndCourseLevel(String category, Level level);
}
