package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.QuizRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.QuizResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Lesson;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Quiz;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.User;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.LessonRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.QuizRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.UserRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    @Override
    public Long createQuiz(QuizRequest quizRequest, Long userId, Long lessonId) {

        Optional<Lesson> lessonOptional =lessonRepository.findById(lessonId);
        if(!lessonOptional.isPresent()){
            throw new RuntimeException("Lesson with id not found");
        }
        Quiz quiz = Quiz.builder()
                .lesson(lessonOptional.get())
                .answer(quizRequest.getAnswer())
                .difficultyLevel(quizRequest.getDifficultyLevel())
                .isSelected(quizRequest.isSelected())
                .option1(quizRequest.getOption1())
                .option2(quizRequest.getOption2())
                .option3(quizRequest.getOption3())
                .option4(quizRequest.getOption4())
                .questionText(quizRequest.getQuestionText())
                .quizLevel(quizRequest.getQuizLevel())
                .build();

        Quiz savedQuiz = quizRepository.save(quiz);
        return savedQuiz.getId();
    }

    @Override
    public List<QuizResponse> getQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return getListOfQuizzes(quizzes);
    }

    @Override
    public List<QuizResponse> getAllQuizzesForALesson(Long lessonId) {
        List<Quiz> quizzes = quizRepository.findAllByLessonId(lessonId);
        return getListOfQuizzes(quizzes);
    }

    @Override
    public QuizResponse getQuiz(Long quizId) {

        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if(!quizOptional.isPresent()){
            throw new RuntimeException("Quiz not found");
        }
        QuizResponse quizResponse = QuizResponse.builder()
                .id(quizOptional.get().getId())
                .questionText(quizOptional.get().getQuestionText())
                .score(quizOptional.get().getScore())
                .option1(quizOptional.get().getOption1())
                .option2(quizOptional.get().getOption2())
                .option3(quizOptional.get().getOption3())
                .option4(quizOptional.get().getOption4())
                .isSelected(quizOptional.get().isSelected())
                .difficultyLevel(quizOptional.get().getDifficultyLevel())
                .answer(quizOptional.get().getAnswer())
                .quizLevel(quizOptional.get().getQuizLevel())
                .build();

        return quizResponse;
    }

    @Override
    public List<QuizResponse> getUserCreatedQuizzes(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()){
            throw new RuntimeException("User not found");
        }
//        List<Quiz> quizzes = quizRepository.findAllByCreatedBy(userOptional.get());
        List<Quiz> quizzes = quizRepository.findAll();
        return getListOfQuizzes(quizzes);
    }

    @Override
    public void updateQuiz(Long quizId, QuizRequest quizRequest) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);

        if(!quizOptional.isPresent()){
            throw new RuntimeException("QuizId  not found");
        }

        quizOptional.get().setOption1(quizRequest.getOption1());
        quizOptional.get().setOption2(quizRequest.getOption2());
        quizOptional.get().setOption3(quizRequest.getOption3());
        quizOptional.get().setOption4(quizRequest.getOption4());
        quizOptional.get().setAnswer(quizRequest.getAnswer());
        quizOptional.get().setDifficultyLevel(quizRequest.getDifficultyLevel());
        quizOptional.get().setSelected(quizRequest.isSelected());
        quizOptional.get().setQuestionText(quizRequest.getQuestionText());
        quizOptional.get().setScore(quizRequest.getScore());
        quizOptional.get().setQuizLevel(quizRequest.getQuizLevel());

        quizRepository.save(quizOptional.get());
    }

    @Override
    public void deleteQuiz(Long quizId) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);

        if(!quizOptional.isPresent()){
            throw new RuntimeException("QuizId  not found");
        }
        quizRepository.deleteById(quizId);
    }

    private List<QuizResponse> getListOfQuizzes(List<Quiz> quizList){
        List<QuizResponse> quizResponses = new ArrayList<>();

        for(Quiz quiz: quizList) {

            QuizResponse quizResponse = QuizResponse.builder()
                    .id(quiz.getId())
                    .answer(quiz.getAnswer())
                    .difficultyLevel(quiz.getDifficultyLevel())
                    .isSelected(quiz.isSelected())
                    .option1(quiz.getOption1())
                    .option2(quiz.getOption2())
                    .option3(quiz.getOption3())
                    .option4(quiz.getOption4())
                    .score(quiz.getScore())
                    .quizLevel(quiz.getQuizLevel())
                    .questionText(quiz.getQuestionText())
                    .build();

            quizResponses.add(quizResponse);
        }
        return quizResponses;
    }
}