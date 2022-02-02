package com.emsi.pfa.elearning.web;

import com.emsi.pfa.elearning.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @Autowired
    private PostService postService;
}
