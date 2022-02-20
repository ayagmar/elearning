package com.emsi.pfa.elearning.web;

import com.emsi.pfa.elearning.model.Comment;
import com.emsi.pfa.elearning.model.Post;
import com.emsi.pfa.elearning.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllPosts() {
        return commentService.getAllComments();
    }
    @GetMapping("/post/{id}")
    public ResponseEntity<List<Comment>> getCommentByPostID(@PathVariable Long id) {
        return commentService.getAllCommentsByPostID(id);
    }
}
