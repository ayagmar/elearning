package com.emsi.pfa.elearning.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String typeDocument;
    @CreationTimestamp
    private LocalDateTime uploadDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private byte[] content;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Post post;

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Comment comment;

}