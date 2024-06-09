package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.ModuleRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.ModuleResponse;

import java.util.List;

public interface ModuleService {

    Long createModule(ModuleRequest moduleRequest, Long userId, Long courseId);

    List<ModuleResponse> getModules();

    List<ModuleResponse> getAllModulesForACourse(Long courseId);

    ModuleResponse getModule(Long moduleId);

    List<ModuleResponse> getUserCreatedModule(Long userId);

    void updateModule(Long courseId, ModuleRequest moduleRequest);

    void deleteModule(Long moduleId);
}
