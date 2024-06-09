package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.requestDto;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Level;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LessonRequest {
    private String title;
    private String videoUrl;

    @Enumerated(EnumType.STRING)
    Level lessonLevel;
    @Lob
    private String content;
}