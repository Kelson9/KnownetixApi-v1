package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.controller;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto.ModuleRequest;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto.ModuleResponse;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.services.UserDetailsImpl;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.ModuleService;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/module")
public class ModuleController {

    private final ModuleService moduleService;
    private final UserService userService;

    @PostMapping("/{courseId}")
    public ResponseEntity<Long> createModule(@RequestBody ModuleRequest moduleRequest, @PathVariable Long courseId){
      UserDetailsImpl userDetails = userService.getUserDetails();
      return ResponseEntity.ok(moduleService.createModule(moduleRequest, userDetails.getId(), courseId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ModuleResponse>> getModules(){
        return ResponseEntity.ok(moduleService.getModules());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ModuleResponse>> getAllModulesForACourse(@PathVariable Long courseId){
        return ResponseEntity.ok(moduleService.getAllModulesForACourse(courseId));
    }

    @GetMapping("/{moduleId}")
    public ResponseEntity<ModuleResponse> getModule(@PathVariable Long moduleId){
        return ResponseEntity.ok(moduleService.getModule(moduleId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<ModuleResponse>> getUserCreatedModule(){
        UserDetailsImpl userDetails = userService.getUserDetails();
        return ResponseEntity.ok(moduleService.getUserCreatedModule(userDetails.getId()));
    }

    @PutMapping("/update/{moduleId}")
    public ResponseEntity<?> updateModule(@RequestBody ModuleRequest moduleRequest, @PathVariable Long moduleId){
        moduleService.updateModule(moduleId, moduleRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{moduleId}")
    public ResponseEntity<?> deleteModule(@PathVariable Long moduleId) {
        moduleService.deleteModule(moduleId);
        return ResponseEntity.noContent().build();
    }
}