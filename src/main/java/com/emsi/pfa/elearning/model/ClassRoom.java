package com.emsi.pfa.elearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer category;
    private String branch;
    @ManyToMany
    private Collection<User> users = new ArrayList<>();
    @ManyToMany
    private Collection<User> professors = new ArrayList<>();

    @OneToMany(mappedBy = "classroom")
    private Collection<Course> courses = new ArrayList<>();

}
