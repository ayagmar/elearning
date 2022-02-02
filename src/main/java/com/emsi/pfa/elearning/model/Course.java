package com.emsi.pfa.elearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String courseCode;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "course")
    private Collection<Post> posts;

    @ManyToOne
    private User professor;

    @ManyToOne
    private ClassRoom classroom;

    @ManyToMany
    private Collection<User> users=new ArrayList<>();



}
