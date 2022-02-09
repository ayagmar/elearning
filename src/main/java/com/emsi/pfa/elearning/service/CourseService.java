package com.emsi.pfa.elearning.service;

import com.emsi.pfa.elearning.dao.ClassRepository;
import com.emsi.pfa.elearning.dao.CourseRepository;
import com.emsi.pfa.elearning.dao.UserRepository;
import com.emsi.pfa.elearning.model.ClassRoom;
import com.emsi.pfa.elearning.model.Course;
import com.emsi.pfa.elearning.model.User;
import com.emsi.pfa.elearning.model.Util.FormHelperClass;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
public class CourseService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  ClassRepository classRepository;
    @Autowired
    private  CourseRepository courseRepository;



    public ResponseEntity<String> CreateCourse(FormHelperClass.formCourse fCourse){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        Course course=new Course();
        course.setName(fCourse.getCourseName());
        course.setProfessor(userRepository.findByUsername(fCourse.getProfessorName()));
        course.setCourseCode(UUID.randomUUID().toString().replaceAll("-", "").trim().substring(0,6));
        courseRepository.save(course);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/test").toUriString());
        return ResponseEntity.created(uri).body("Course created sucessfully");
    }

    public ResponseEntity<String> enrollCourse(String code){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        Course course=courseRepository.findByCourseCode(code);
        Collection<Course> courses=LoggedInUser.getCourses();
        if(courses.contains(course) ){
            return ResponseEntity.badRequest().body("you are already enrolled in this course");
        }
        LoggedInUser.getCourses().add(course);
        return ResponseEntity.ok().body("Enrolled into course sucessfully");
    }

    public ResponseEntity<Collection<Course>> userGetCourses(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        return ResponseEntity.ok().body(LoggedInUser.getCourses());
    }

    public ResponseEntity<Collection<Course>> professorGetCourses(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        return ResponseEntity.ok().body(LoggedInUser.getProfCourses());
    }

    public ResponseEntity<Collection<Course>> getCoursesByClassRoomID(Long id) {
        ClassRoom classRoom=classRepository.getById(id);
        return ResponseEntity.ok().body(classRoom.getCourses());
    }

    public ResponseEntity<Integer> getTotalCourseNumber(){
        return ResponseEntity.ok().body(courseRepository.findAll().size());
    }

    public ResponseEntity<Object> getCourseNumberByClassRoomID(String name){
        Optional<ClassRoom> classRoom=classRepository.findClassRoomByName(name);
        return classRoom.<ResponseEntity<Object>>map(room ->
                ResponseEntity.ok().body(room.getCourses().size())).orElseGet(() ->
                ResponseEntity.badRequest().body("ClassRoom Does not exist"));
    }


}

