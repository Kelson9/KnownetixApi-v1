package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
public class QuizActivityLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "quizId")
    private Quiz quiz;
    private double grade;
    private Long difficultyLevel;
}
