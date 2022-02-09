package com.emsi.pfa.elearning.service;


import com.emsi.pfa.elearning.dao.CommentRepository;
import com.emsi.pfa.elearning.dao.CourseRepository;
import com.emsi.pfa.elearning.dao.PostRepository;
import com.emsi.pfa.elearning.dao.UserRepository;
import com.emsi.pfa.elearning.model.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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


    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    public ResponseEntity<Integer> getAllUsersCount() {
        return ResponseEntity.ok().body(userRepository.findAll().size());
    }

    public ResponseEntity<List<User>> getAllUsersByRoleId(Long id) {
        return ResponseEntity.ok().body(userRepository.findByRoles_Id(id));
    }

    public ResponseEntity<Integer> getAllUsersCountByRoleId(Long id) {
        return ResponseEntity.ok().body(userRepository.findByRoles_Id(id).size());
    }

}
