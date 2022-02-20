package com.emsi.pfa.elearning.dao;

import com.emsi.pfa.elearning.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPost_PostId(Long id);
}