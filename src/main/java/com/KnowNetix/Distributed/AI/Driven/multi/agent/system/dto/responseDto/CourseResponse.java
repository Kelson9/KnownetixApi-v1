package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Level;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CourseResponse {
        private Long id;

        private String title;

        private String duration;

        private String description;

        private String instructor;

        private String imageUrl;

        @Enumerated(EnumType.STRING)
        private Level courseLevel;

        List<ModuleResponse> moduleResponse;
}