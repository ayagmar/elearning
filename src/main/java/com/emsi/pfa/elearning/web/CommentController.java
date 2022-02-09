package com.emsi.pfa.elearning.web;

import com.emsi.pfa.elearning.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
}
