package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.dto.responseDto;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.Level;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class LessonResponse {
    private Long id;
    private String title;
    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    Level lessonLevel;
    private String videoUrl;
    List<QuizResponse> quizResponses;
}
