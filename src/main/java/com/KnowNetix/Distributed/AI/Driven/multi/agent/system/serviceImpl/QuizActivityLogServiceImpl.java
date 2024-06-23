package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.QuizActivityLogRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.exception.ErrorCodes;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.exception.ResourceNotFoundException;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Quiz;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.QuizActivityLog;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.QuizActivityLogRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.QuizRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.QuizActivityLogService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizActivityLogServiceImpl implements QuizActivityLogService {

    private final QuizActivityLogRepository quizActivityLogRepository;

    private final UserService userService;

    private final QuizRepository quizRepository;

    @Override
    public QuizActivityLog getQuizActivityLogById(Long logId) {
        Optional<QuizActivityLog> quizActivityLog = quizActivityLogRepository.findById(logId);
        if(!quizActivityLog.isPresent()){
            throw new ResourceNotFoundException(ErrorCodes.RESOURCE_NOT_FOUND.getMessage());
        }
        return  quizActivityLog.get();
    }

    @Override
    public List<QuizActivityLog> getAllQuizActivityLogs() {

        return quizActivityLogRepository.findAll();
    }

    @Override
    public List<QuizActivityLog> getQuizActivityLogsByUser(Long userId) {
        return quizActivityLogRepository.findAllByUserId(userId);
    }

    @Override
    public List<QuizActivityLog> getQuizActivityLogsByLesson(Long quizId) {

        return quizActivityLogRepository.findAllByQuizId(quizId);
    }

    @Override
    public void deleteQuizActivityLog(Long logId) {
        QuizActivityLog activityLog  = getQuizActivityLogById(logId);
        quizActivityLogRepository.delete(activityLog);
    }

    @Override
    public void SaveQuizActivityLog(QuizActivityLogRequest quizActivityLogRequest) {
        QuizActivityLog activityLog = new QuizActivityLog();
        activityLog.setGrade(quizActivityLogRequest.getGrade());
        activityLog.setLogId(quizActivityLogRequest.getQuizId());


        activityLog.setLogId(quizActivityLogRequest.getQuizId());
        activityLog.setGrade(quizActivityLogRequest.getGrade());
        activityLog.setDifficultyLevel(quizActivityLogRequest.getDifficultyLevel());
        activityLog.setQuiz(getAQuiz(quizActivityLogRequest.getQuizId()));
        activityLog.setUser(userService.getUser(quizActivityLogRequest.getQuizId()));

        quizActivityLogRepository.save(activityLog);
    }

    public Quiz getAQuiz(Long quizId){
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if(!quizOptional.isPresent()){
            throw new ResourceNotFoundException(ErrorCodes.RESOURCE_NOT_FOUND.getMessage());
        }
        return quizOptional.get();
    }
}