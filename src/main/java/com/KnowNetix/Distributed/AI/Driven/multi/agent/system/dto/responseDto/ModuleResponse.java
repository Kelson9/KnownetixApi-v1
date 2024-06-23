package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ModuleResponse {
    private Long id;
    private String title;
    private String imageUrl;
    private String duration;
    List<LessonResponse> lessonResponses;
}
