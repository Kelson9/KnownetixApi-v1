package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
public class Module extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String imageUrl;

    private String duration;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private List<Lesson> lessons;

    @ManyToOne
    @JoinColumn(name = "_user_id")
    private User createdBy;

}
