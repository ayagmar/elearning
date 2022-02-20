package com.emsi.pfa.elearning.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String description;
    private Integer nb_documents = 0;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    private Course course;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "post")
    private Collection<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "post")
    private Collection<Document> documents = new ArrayList<>();
    @ManyToOne
    private User user;


}
