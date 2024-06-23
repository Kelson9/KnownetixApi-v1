package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonActivityLog  extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "lessonId")
    private Lesson lesson;
    private String timeSpent;
    private double grade;
}
