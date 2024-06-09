package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.ModuleRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.CourseResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.ModuleResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Course;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Module;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.User;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.CourseRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.ModuleRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.UserRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.CourseService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.LessonService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final LessonService lessonService;

    @Override
    public Long createModule(ModuleRequest moduleRequest, Long userId, Long courseId) {
        Optional<Course> courseOptional =courseRepository.findById(courseId);
        if(!courseOptional.isPresent()){
            throw new RuntimeException("Course with id not found");
        }
        Module module = Module.builder()
                .title(moduleRequest.getTitle())
                .duration(moduleRequest.getDuration())
                .imageUrl(moduleRequest.getImageUrl())
                .course(courseOptional.get())
                .build();

        Module savedModule = moduleRepository.save(module);
        return savedModule.getId();
    }

    @Override
    public List<ModuleResponse> getModules() {
        List<Module> modules = moduleRepository.findAll();
        return getListOfModules(modules);
    }

    @Override
    public List<ModuleResponse> getAllModulesForACourse(Long courseId) {
        List<Module> modules = moduleRepository.findAllByCourseId(courseId);
        return getListOfModules(modules);
    }

    @Override
    public ModuleResponse getModule(Long moduleId) {

        Optional<Module> moduleOptional = moduleRepository.findById(moduleId);
        if(!moduleOptional.isPresent()){
            throw new RuntimeException("Module not found");
        }
        ModuleResponse moduleResponse = ModuleResponse.builder()
                .id(moduleOptional.get().getId())
                .title(moduleOptional.get().getTitle())
                .duration(moduleOptional.get().getDuration())
                .imageUrl(moduleOptional.get().getImageUrl())
                .lessonResponses(lessonService.getAllLessonsForAModule(moduleId))
                .build();

        return moduleResponse;
    }

    @Override
    public List<ModuleResponse> getUserCreatedModule(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()){
            throw new RuntimeException("User not found");
        }
        List<Module> moduleList = moduleRepository.findAllByCreatedBy(userOptional.get());
        return getListOfModules(moduleList);
    }

    @Override
    public void updateModule(Long courseId, ModuleRequest moduleRequest) {
        Optional<Module> moduleOptional = moduleRepository.findById(courseId);

        if(!moduleOptional.isPresent()){
            throw new RuntimeException("ModuleId  not found");
        }

        moduleOptional.get().setDuration(moduleRequest.getDuration());
        moduleOptional.get().setImageUrl(moduleRequest.getImageUrl());
        moduleOptional.get().setTitle(moduleRequest.getTitle());

        moduleRepository.save(moduleOptional.get());
    }

    @Override
    public void deleteModule(Long moduleId) {
        Optional<Module> moduleOptional = moduleRepository.findById(moduleId);

        if(!moduleOptional.isPresent()){
            throw new RuntimeException("ModuleId  not found");
        }
        moduleRepository.deleteById(moduleId);
    }

    private List<ModuleResponse> getListOfModules(List<Module> modules){
        List<ModuleResponse> moduleResponses = new ArrayList<>();

        for(Module module: modules) {

            ModuleResponse moduleResponse = ModuleResponse.builder()
                    .id(module.getId())
                    .title(module.getTitle())
                    .imageUrl(module.getImageUrl())
                    .duration(module.getDuration())
                    .lessonResponses(lessonService.getAllLessonsForAModule(module.getId()))
                    .build();

            moduleResponses.add(moduleResponse);
        }
        return moduleResponses;
    }
}