package com.emsi.pfa.elearning.service;

import com.emsi.pfa.elearning.dao.CourseRepository;
import com.emsi.pfa.elearning.dao.DocumentRepository;
import com.emsi.pfa.elearning.dao.ExamRepository;
import com.emsi.pfa.elearning.dao.UserRepository;
import com.emsi.pfa.elearning.model.Course;
import com.emsi.pfa.elearning.model.Document;
import com.emsi.pfa.elearning.model.Exam;
import com.emsi.pfa.elearning.model.User;
import com.emsi.pfa.elearning.model.Util.FormHelperClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Transactional
public class ExamService {

    public static final String DIRECTORY = System.getProperty("user.home") + "/Documents/uploads";


    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;


    public ResponseEntity<String> addExam(FormHelperClass.formExam form, List<MultipartFile> multipartFile) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        Course course = courseRepository.getById(form.getCourseID());
        Collection<Course> courses = LoggedInUser.getProfCourses();
        if (!courses.contains(course)) {
            return ResponseEntity.badRequest().body("cannot post this course is not created by you !");
        }

        Exam exam = new Exam();
        exam.setCourseExam(course);
        exam.setName(form.getExamName());
        exam.setStartDate(LocalDateTime.parse(form.getStartDate()));
        exam.setDuration(form.getHours());
        exam.setEndDate(LocalDateTime.parse(form.getStartDate()).plusHours(form.getHours()));
        examRepository.save(exam);
        for (MultipartFile file : multipartFile) {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            File folder = new File(DIRECTORY + "/" + " " + exam.getName() + " for course " + exam.getCourseExam().getName());
            if (folder.mkdir()) {
                System.out.println("dossier created");
            } else {
                System.out.println("error creating folder");
            }
            Path path = Paths.get(String.valueOf(folder), filename).toAbsolutePath().normalize();
            Document document = new Document();
            document.setExam(exam);
            document.setTypeDocument(file.getContentType());
            document.setName(filename);
            document.setContent(file.getBytes());
            copy(file.getInputStream(), path, REPLACE_EXISTING);
            documentRepository.save(document);
        }

        return ResponseEntity.ok().body("Exam has been created!");
    }

    public ResponseEntity<List<Exam>> getAllExams() {
        return ResponseEntity.ok().body(examRepository.findAll());
    }

    public ResponseEntity<Exam> getExamsByCourseID(Long id) {
        return ResponseEntity.ok().body(examRepository.findExamByCourseExamId(id));
    }
}
