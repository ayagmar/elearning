package com.emsi.pfa.elearning.service;

import com.emsi.pfa.elearning.dao.*;
import com.emsi.pfa.elearning.model.ClassRoom;
import com.emsi.pfa.elearning.model.Course;
import com.emsi.pfa.elearning.model.Post;
import com.emsi.pfa.elearning.model.User;
import com.emsi.pfa.elearning.model.Util.FormHelperClass;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

@Service
@Transactional
public class ClassRoomService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  CourseRepository courseRepository;
    @Autowired
    private  CommentRepository commentRepository;
    @Autowired
    private  PostRepository postRepository;

    private ClassRepository classRepository;

    public void CreateClassroom(FormHelperClass.formClass form){
        ClassRoom classRoom=new ClassRoom();
        classRoom.setName(form.getName());
        form.getStudents().forEach(student-> classRoom.getUsers().add(userRepository.findByUsername(student)));
        form.getProfessors().forEach(professor-> classRoom.getProfessors().add(userRepository.findByUsername(professor)));
        classRepository.save(classRoom);
    }

    private ResponseEntity<Integer> getClassRoomsNumber(){
        return ResponseEntity.ok().body(classRepository.findAll().size());
    }


}
