package com.emsi.pfa.elearning.service;

import com.emsi.pfa.elearning.dao.*;
import com.emsi.pfa.elearning.model.Comment;
import com.emsi.pfa.elearning.model.Course;
import com.emsi.pfa.elearning.model.Post;
import com.emsi.pfa.elearning.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class CommentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ClassRepository classRepository;

    public String postComment(Long postId, String mycomment) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        Post post = postRepository.getById(postId);
        Course course = post.getCourse();
        Collection<Course> courses = LoggedInUser.getCourses();
        if (!courses.contains(course)) {
            return "you cannot comment in a post for a course you are not enrolled in";
        }
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setText(mycomment);
        comment.setCommentUser(LoggedInUser);
        commentRepository.save(comment);
        return "Added your Comment into post sucessfully";
    }

    public String deleteComment(Long commentID) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        Comment comment = commentRepository.getById(commentID);
        Collection<Comment> comments = LoggedInUser.getComments();
        if (!comments.contains(comment)) {
            return "Cannot delete comment";
        }
        commentRepository.delete(comment);
        commentRepository.flush();
        return "Comment deleted successfully";
    }

    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok().body(commentRepository.findAll());
    }

    public ResponseEntity<List<Comment>> getAllCommentsByPostID(Long id) {
        return ResponseEntity.ok().body(commentRepository.findCommentsByPost_PostId(id));
    }


}
