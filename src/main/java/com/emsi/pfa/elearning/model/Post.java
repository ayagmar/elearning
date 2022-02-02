package com.emsi.pfa.elearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String description;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    private Course course;
    @OneToMany(mappedBy = "post")
    private Collection<Comment> comments;

    @ManyToOne
    private User user;





}
