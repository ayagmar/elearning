package com.emsi.pfa.elearning.dao;

import com.emsi.pfa.elearning.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface ExamRepository extends JpaRepository<Exam, Long> {
}