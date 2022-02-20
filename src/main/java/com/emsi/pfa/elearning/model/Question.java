package com.emsi.pfa.elearning.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@TypeDefs({

        @TypeDef(name = "json", typeClass = JsonBinaryType.class)
})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "json")
    @Type(type = "json")
    private List<String> reponses;
    @Column(columnDefinition = "json")
    @Type(type = "json")
    private List<String> reponsesCorrect;

    @ManyToOne
    private Devoir devoir;

}
