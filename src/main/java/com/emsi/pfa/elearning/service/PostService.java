package com.emsi.pfa.elearning.service;

import com.emsi.pfa.elearning.dao.CourseRepository;
import com.emsi.pfa.elearning.dao.PostRepository;
import com.emsi.pfa.elearning.dao.UserRepository;
import com.emsi.pfa.elearning.model.Course;
import com.emsi.pfa.elearning.model.Post;
import com.emsi.pfa.elearning.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    public String CreatePost(Long courseID, Post newPost){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        Course course=courseRepository.getById(courseID);
        Collection<Course> courses=LoggedInUser.getCourses();
        if(!courses.contains(course)){
            return "cannot post this course is not created by you !";
        }
        newPost.setCourse(course);
        postRepository.save(newPost);
        return "Post created!";
    }
}
