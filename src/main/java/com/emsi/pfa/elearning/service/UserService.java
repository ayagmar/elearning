package com.emsi.pfa.elearning.service;


import com.emsi.pfa.elearning.dao.CommentRepository;
import com.emsi.pfa.elearning.dao.CourseRepository;
import com.emsi.pfa.elearning.dao.PostRepository;
import com.emsi.pfa.elearning.dao.UserRepository;
import com.emsi.pfa.elearning.model.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional

public class UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  CourseRepository courseRepository;
    @Autowired
    private  CommentRepository commentRepository;
    @Autowired
    private  PostRepository postRepository;

    




}
