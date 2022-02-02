package com.emsi.pfa.elearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 40)
    private String firstName;
    @Column(nullable = false, length = 40)
    private String lastName;
    @Column(unique = true, length = 40, nullable = false)
    private String username;
    @Column(unique = true, length = 40, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private Boolean enabled=true;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Role> roles = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private Collection<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "professor")
    private Collection<Course> ProfCourses=new ArrayList<>();

    @OneToMany(mappedBy = "userC")
    private Collection<Comment> comments=new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private Collection<ClassRoom> userClassrooms=new ArrayList<>();

    @ManyToMany(mappedBy = "professors")
    private Collection<ClassRoom> professorClassrooms=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private Collection<Post> ProfessorPosts=new ArrayList<>();

}