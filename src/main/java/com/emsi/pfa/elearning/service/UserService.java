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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional

public class UserService {
    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
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


    public ResponseEntity<String> getADeleteUserByID(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().body("User was deleted by Succes ");
    }

    public ResponseEntity<String> updateUser(Long id, User user) {

        Optional<User>  userDB_1 = userRepository.findById(id);
        if (userDB_1.isEmpty()){
            return ResponseEntity.badRequest().body("Not Found ");
        }
        User userDB = userDB_1.get();
        if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
            userDB.setEmail(user.getEmail());
        }
        if (Objects.nonNull(user.getPassword()) &&
                !"".equalsIgnoreCase(user.getPassword())) {
            userDB.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        if (Objects.nonNull(user.getUsername()) &&
                !"".equalsIgnoreCase(user.getUsername())) {
            userDB.setUsername(user.getUsername());
        }

        userDB.setFirstName(user.getFirstName());
        userDB.setLastName(user.getLastName());
        userRepository.save(userDB);
        return ResponseEntity.ok().body("User was Updated with succes");
    }
}
