package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.agent.UserAgent;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.LessonRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.LessonResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.*;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Module;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.LessonRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.ModuleRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.UserRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.LessonService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ModuleRepository moduleRepository;
    private final UserRepository userRepository;
    private final QuizService quizService;
    private final LearningPathRecommender recommender;

    private final UserAgent userAgent;

    @Override
    public Long createLesson(LessonRequest lessonRequest, Long userId, Long moduleId) {
        Optional<Module> moduleOptional =moduleRepository.findById(moduleId);
        if(!moduleOptional.isPresent()){
            throw new RuntimeException("Module with id not found");
        }
        Lesson lesson = Lesson.builder()
                .title(lessonRequest.getTitle())
                .content(lessonRequest.getContent())
                .videoUrl(lessonRequest.getVideoUrl())
                .lessonLevel(lessonRequest.getLessonLevel())
                .module(moduleOptional.get())
                .build();

        Lesson savedLesson = lessonRepository.save(lesson);
        return savedLesson.getId();
    }

    @Override
    public List<LessonResponse> getLessons() {
        List<Lesson> lessons = lessonRepository.findAll();

//        int userState = Integer.parseInt(userAgent.getUserCognitiveState());
        int userState = 4;
        Recommendation recommendation = recommender.recommendLearningPath(userState);
        System.out.println("Recommended action: " + recommendation.getAction());
        System.out.println("Learning materials: " + recommendation.getLearningMaterials());

        System.out.println("\nQ-table:");
        double[][] qTable = recommender.getQTable();
        for (double[] row : qTable) {
            System.out.println(Arrays.toString(row));
        }
        return getListOfLessons(lessons);
    }

    @Override
    public List<LessonResponse> getAllLessonsForAModule(Long moduleId) {
        List<Lesson> lessons = lessonRepository.findAllByModuleId(moduleId);
        return getListOfLessons(lessons);
    }

    @Override
    public LessonResponse getLesson(Long lessonId) {

        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);
        if(!lessonOptional.isPresent()){
            throw new RuntimeException("Lesson not found");
        }
        LessonResponse lessonResponse = LessonResponse.builder()
                .id(lessonOptional.get().getId())
                .title(lessonOptional.get().getTitle())
                .videoUrl(lessonOptional.get().getVideoUrl())
                .lessonLevel(lessonOptional.get().getLessonLevel())
                .quizResponses(quizService.getAllQuizzesForALesson(lessonId))
                .build();

        return lessonResponse;
    }

    @Override
    public List<LessonResponse> getUserCreatedLessons(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()){
            throw new RuntimeException("User not found");
        }
        List<Lesson> lessons = lessonRepository.findAllByCreatedBy(userOptional.get());
        return getListOfLessons(lessons);
    }

    @Override
    public void updateLesson(Long courseId, LessonRequest lessonRequest) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(courseId);

        if(!lessonOptional.isPresent()){
            throw new RuntimeException("LessonId  not found");
        }

        lessonOptional.get().setContent(lessonRequest.getContent());
        lessonOptional.get().setTitle(lessonRequest.getTitle());
        lessonOptional.get().setVideoUrl(lessonRequest.getVideoUrl());
        lessonOptional.get().setLessonLevel(lessonRequest.getLessonLevel());

        lessonRepository.save(lessonOptional.get());
    }

    @Override
    public void deleteLesson(Long lessonId) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);

        if(!lessonOptional.isPresent()){
            throw new RuntimeException("LessonId  not found");
        }
        lessonRepository.deleteById(lessonId);
    }

    private List<LessonResponse> getListOfLessons(List<Lesson> lessons){
        List<LessonResponse> lessonResponses = new ArrayList<>();

        for(Lesson lesson: lessons) {

            LessonResponse lessonResponse = LessonResponse.builder()
                    .id(lesson.getId())
                    .title(lesson.getTitle())
                    .content(lesson.getContent())
                    .videoUrl(lesson.getVideoUrl())
                    .lessonLevel(lesson.getLessonLevel())
                    .quizResponses(quizService.getAllQuizzesForALesson(lesson.getId()))
                    .build();

            lessonResponses.add(lessonResponse);
        }
        return lessonResponses;
    }
}