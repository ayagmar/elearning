package com.emsi.pfa.elearning.service;

import com.emsi.pfa.elearning.dao.CourseRepository;
import com.emsi.pfa.elearning.dao.DocumentRepository;
import com.emsi.pfa.elearning.dao.PostRepository;
import com.emsi.pfa.elearning.dao.UserRepository;
import com.emsi.pfa.elearning.model.*;
import com.emsi.pfa.elearning.model.Util.FormHelperClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PostService {

    public static final String DIRECTORY = System.getProperty("user.home") + "/Documents/uploads";

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DocumentRepository documentRepository;

    public String CreatePost(FormHelperClass.postForm form, List<MultipartFile> multipartFile) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        Course course=courseRepository.getById(form.getCourseID());
        Collection<Course> courses=LoggedInUser.getProfCourses();
        if(!courses.contains(course)){
            return "cannot post this course is not created by you !";
        }

        Post newPost=new Post();
        Notification postNotification = new Notification();
        newPost.setCourse(course);
        newPost.setDescription(form.getDescription());
        newPost.setTitle(form.getPostTitle());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUser(LoggedInUser);
        postRepository.save(newPost);
        for (MultipartFile file : multipartFile) {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
             File folder = new File(DIRECTORY +"/"+ "Course "+course.getName()+" posts for user "+LoggedInUser.getUsername());
             if(folder.mkdir()){
            System.out.println("dossier created");
              }
             else{
                 System.out.println("error creating folder");
              }
             Path path = Paths.get(String.valueOf(folder), filename).toAbsolutePath().normalize();
            Document document = new Document();
            document.setPost(newPost);
            document.setTypeDocument(file.getContentType());
            document.setName(filename);
            document.setContent(file.getBytes());
            document.getPost().setNb_documents(document.getPost().getNb_documents() + 1);
            copy(file.getInputStream(), path, REPLACE_EXISTING);
            documentRepository.save(document);
        }

        return "Post created!";
    }
}
