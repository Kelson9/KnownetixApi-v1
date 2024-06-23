package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Data
public class User  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    @Column
    private boolean isEnabled;

    @Column
    private Double cognitiveLevel;

    @Column
    private String levelOfEducation;

    @Column
    private String interest;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "_user_roles",
            joinColumns = @JoinColumn(name = "_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column
    private String telephoneNumber;

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private List<Lesson> lessons = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private List<Quiz> quizzes = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private List<Assessment> assessments = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    @JsonIgnore
    private List<Lesson> createdLessons = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    @JsonIgnore
    private List<Module> createdModules = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    @JsonIgnore
    private List<Course> createdCourses = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    @JsonIgnore
    private List<Assessment> createdAssessments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<ActivityLog> activityLogs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<LessonActivityLog> lessonActivityLogs = new ArrayList<>();

    public User(String username, String email, String password,String telephoneNumber, String levelOfEducation, String interest) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.telephoneNumber=telephoneNumber;
        this.levelOfEducation = levelOfEducation;
        this.interest = interest;
    }
}