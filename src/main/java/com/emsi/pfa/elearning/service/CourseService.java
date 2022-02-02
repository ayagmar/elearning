package com.emsi.pfa.elearning.service;

import com.emsi.pfa.elearning.dao.ClassRepository;
import com.emsi.pfa.elearning.dao.CourseRepository;
import com.emsi.pfa.elearning.dao.UserRepository;
import com.emsi.pfa.elearning.model.ClassRoom;
import com.emsi.pfa.elearning.model.Course;
import com.emsi.pfa.elearning.model.User;
import com.emsi.pfa.elearning.model.Util.FormHelperClass;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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



    public String CreateCourse(String courseName){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        Course course=new Course();
        course.setName(courseName);
        course.setProfessor(LoggedInUser);
        course.setCourseCode(UUID.randomUUID().toString().replaceAll("-", "").trim().substring(0,6));
        courseRepository.save(course);
        return "Course created sucessfully";
    }

    public String enrollCourse(String code){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        Course course=courseRepository.findByCourseCode(code);
        Collection<Course> courses=LoggedInUser.getCourses();

        if(courses.contains(course) ){
            return "you are already enrolled in this course";
        }
        LoggedInUser.getCourses().add(course);
        return "Enrolled into course sucessfully";
    }

}

