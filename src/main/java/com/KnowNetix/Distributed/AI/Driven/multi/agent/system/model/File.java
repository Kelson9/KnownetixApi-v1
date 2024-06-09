package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String path;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
}
