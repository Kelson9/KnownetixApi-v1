package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
@Data
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String duration;

    private String description;

    private String instructor;

    private String category;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    Level courseLevel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Module> modules;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Assessment> assessments;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Enrollment> enrollments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "_user_id")
    private User createdBy;

    @ManyToOne
    CourseCategory courseCategory;
}
