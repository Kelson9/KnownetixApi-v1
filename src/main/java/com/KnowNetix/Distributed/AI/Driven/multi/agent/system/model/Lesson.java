package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
public class Lesson extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    private String videoUrl;

    @Enumerated(EnumType.STRING)
    Level lessonLevel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    private List<File> files;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    private List<Quiz> quizzes;

    @ManyToMany
    @JoinTable(name = "_user_lessons",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "_user_id"))
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "_user_id")
    private User createdBy;
}