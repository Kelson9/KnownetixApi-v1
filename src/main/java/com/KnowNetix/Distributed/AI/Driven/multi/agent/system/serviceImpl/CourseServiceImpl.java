package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.agent.RecommenderAgent;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.agent.UserAgent;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.CourseRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.CourseResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.LessonResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.ModuleResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.QuizResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.exception.ResourceNotFoundException;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.*;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Module;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.CourseCategoryRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.CourseRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.UserRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.CourseService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.ModuleService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ModuleService moduleService;

    private final UserAgent userAgent;

    private final RecommenderAgent recommenderAgent;

    private final QuizService quizService;

    private final CourseCategoryRepository courseCategoryRepository;

    @Override
    public Long createCourse(CourseRequest courseRequest, Long userId, Long categoryId, Level level) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }

        Optional<CourseCategory> courseCategory = courseCategoryRepository.findById(categoryId);

        if (!courseCategory.isPresent()) {
            throw new ResourceNotFoundException("Course Category not found not found");
        }

        Course course = Course.builder()
                .title(courseRequest.getTitle())
                .description(courseRequest.getDescription())
                .duration(courseRequest.getDuration())
                .imageUrl(courseRequest.getImageUrl())
                .courseLevel(level)
                .instructor(courseRequest.getInstructor())
                .courseCategory(courseCategory.get())
                .createdBy(userOptional.get())
                .build();

        Course savedCourse = courseRepository.save(course);

        return savedCourse.getId();
    }

    @Override
    public List<CourseResponse> getCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();

        for (Course course : courses) {

            CourseResponse courseResponse = CourseResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .imageUrl(course.getImageUrl())
                    .duration(course.getDuration())
                    .courseLevel(course.getCourseLevel())
                    .instructor(course.getInstructor())
                    .moduleResponse(moduleService.getAllModulesForACourse(course.getId()))
                    .build();
            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }

    @Override
    public CourseResponse getCourse(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (!courseOptional.isPresent()) {
            throw new RuntimeException("CourseId  not found");
        }

        CourseResponse courseResponse = CourseResponse.builder()
                .id(courseOptional.get().getId())
                .title(courseOptional.get().getTitle())
                .duration(courseOptional.get().getDuration())
                .description(courseOptional.get().getDescription())
                .imageUrl(courseOptional.get().getImageUrl())
                .courseLevel(courseOptional.get().getCourseLevel())
                .moduleResponse(moduleService.getAllModulesForACourse(courseId))
                .instructor(courseOptional.get().getInstructor())
                .build();
        return courseResponse;
    }

    @Override
    public List<CourseResponse> getUserCreatedCourses(Long userId) {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();

        for (Course course : courses) {

            CourseResponse courseResponse = CourseResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .imageUrl(course.getImageUrl())
                    .duration(course.getDuration())
                    .courseLevel(course.getCourseLevel())
                    .moduleResponse(moduleService.getAllModulesForACourse(course.getId()))
                    .instructor(course.getInstructor())
                    .build();

            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }

    @Override
    public void updateCourse(Long courseId, CourseRequest courseRequest, Level level) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (!courseOptional.isPresent()) {
            throw new RuntimeException("CourseId  not found");
        }

        courseOptional.get().setDescription(courseRequest.getDescription());
        courseOptional.get().setDuration(courseRequest.getDuration());
        courseOptional.get().setImageUrl(courseRequest.getImageUrl());
        courseOptional.get().setTitle(courseRequest.getTitle());
        courseOptional.get().setCourseLevel(level);
        courseOptional.get().setInstructor(courseRequest.getInstructor());

        courseRepository.save(courseOptional.get());
    }

    @Override
    public void deleteCourse(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (!courseOptional.isPresent()) {
            throw new RuntimeException("CourseId  not found");
        }
        courseRepository.deleteById(courseId);
    }

    @Override
    public List<CourseResponse> getCoursesByCategory(String category) {
        List<Course> courses = courseRepository.findAllByCategory(category);

        List<CourseResponse> courseResponses = new ArrayList<>();

        for (Course course : courses) {

            CourseResponse courseResponse = CourseResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .imageUrl(course.getImageUrl())
                    .duration(course.getDuration())
                    .courseLevel(course.getCourseLevel())
                    .instructor(course.getInstructor())
                    .moduleResponse(moduleService.getAllModulesForACourse(course.getId()))
                    .build();

            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }

    @Override
    public List<CourseResponse> getUserRecommendedCourses() {
        String category = userAgent.sendUserInterestToRecommenderAgent();
        Level level1 = Level.valueOf(userAgent.sendUserLevelToRecommenderAgent());
        List<Course> courses = courseRepository.findAllByCategoryAndCourseLevel(category, level1);

        List<CourseResponse> courseResponses = new ArrayList<>();

        for (Course course : courses) {

            CourseResponse courseResponse = CourseResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .imageUrl(course.getImageUrl())
                    .duration(course.getDuration())
                    .courseLevel(course.getCourseLevel())
                    .instructor(course.getInstructor())
                    .moduleResponse(moduleService.getAllModulesForACourse(course.getId()))
                    .build();

            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }

    @Override
    public List<CourseResponse> getFilteredCourses(String lessonLevel, String quizLevel) {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> filteredCourseResponses = new ArrayList<>();

        for (Course course : courses) {
            List<ModuleResponse> filteredModuleResponses = new ArrayList<>();

            for (Module module : course.getModules()) {
                List<LessonResponse> filteredLessonResponses = new ArrayList<>();

                for (Lesson lesson : module.getLessons()) {
                    List<QuizResponse> filteredQuizResponses = new ArrayList<>();

                    for (Quiz quiz : lesson.getQuizzes()) {
                        if (quizLevel == null || quiz.getQuizLevel().equals(quizLevel)) {
                            filteredQuizResponses.add(QuizResponse.builder()
                                    .id(quiz.getId())
                                    .questionText(quiz.getQuestionText())
                                    .option1(quiz.getOption1())
                                    .option2(quiz.getOption2())
                                    .option3(quiz.getOption3())
                                    .option4(quiz.getOption4())
                                    .answer(quiz.getAnswer())
                                    .difficultyLevel(quiz.getDifficultyLevel())
                                    .score(quiz.getScore())
                                    .quizLevel(quiz.getQuizLevel())
                                    .isSelected(quiz.isSelected())
                                    .build());
                        }


                        if (lessonLevel == null || lesson.getLessonLevel().equals(lessonLevel)) {
                            filteredLessonResponses.add(LessonResponse.builder()
                                    .id(lesson.getId())
                                    .title(lesson.getTitle())
                                    .content(lesson.getContent())
                                    .lessonLevel(lesson.getLessonLevel())
                                    .videoUrl(lesson.getVideoUrl())
                                    .quizResponses(filteredQuizResponses)
                                    .build());
                        }
                    }

                    filteredModuleResponses.add(ModuleResponse.builder()
                            .id(module.getId())
                            .title(module.getTitle())
                            .imageUrl(module.getImageUrl())
                            .duration(module.getDuration())
                            .lessonResponses(filteredLessonResponses)
                            .build());
                }

                CourseResponse courseResponse = CourseResponse.builder()
                        .id(course.getId())
                        .title(course.getTitle())
                        .description(course.getDescription())
                        .imageUrl(course.getImageUrl())
                        .duration(course.getDuration())
                        .courseLevel(course.getCourseLevel())
                        .instructor(course.getInstructor())
                        .moduleResponse(filteredModuleResponses)
                        .build();

                filteredCourseResponses.add(courseResponse);
            }

            return filteredCourseResponses;
        }
        return filteredCourseResponses;
    }
}