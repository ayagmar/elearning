package com.emsi.pfa.elearning.service;

import com.emsi.pfa.elearning.dao.*;
import com.emsi.pfa.elearning.model.ClassRoom;
import com.emsi.pfa.elearning.model.Course;
import com.emsi.pfa.elearning.model.User;
import com.emsi.pfa.elearning.model.Util.FormHelperClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ClassRoomService {
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

    public ResponseEntity<List<ClassRoom>> getAll() {
        return ResponseEntity.ok().body(classRepository.findAll());
    }


    public void CreateClassroom(FormHelperClass.formClass form) {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setName(form.getName());
        classRoom.setBranch(form.getBranch());
        classRoom.setCategory(form.getCategory());
        form.getStudents().forEach(student -> classRoom.getUsers().add(userRepository.findByUsername(student)));
        form.getProfessors().forEach(professor -> classRoom.getProfessors().add(userRepository.findByUsername(professor)));
        classRepository.save(classRoom);
    }

    public ResponseEntity<Integer> getClassRoomsCount() {
        return ResponseEntity.ok().body(classRepository.findAll().size());
    }

    public ResponseEntity<List<ClassRoom>> getClassRoomsByCategory(Integer cat) {
        return ResponseEntity.ok().body(classRepository.findClassRoomsByCategory(cat));
    }

    public ResponseEntity<List<ClassRoom>> getClassRoomsByBranch(String branch) {
        return ResponseEntity.ok().body(classRepository.findClassRoomsByBranch(branch));
    }

    public void DeleteClassroom(Long id) {
        classRepository.deleteById(id);
    }

    public void AddStudent(Long id, FormHelperClass.formClass form) {
        ClassRoom classRoom = classRepository.getById(id);
        form.getStudents().forEach(e -> classRoom.getUsers().add(userRepository.findByUsername(e)));
    }

    public void AddProfessor(Long id, FormHelperClass.formClass form) {
        ClassRoom classRoom = classRepository.getById(id);
        form.getProfessors().forEach(p -> classRoom.getProfessors().add(userRepository.findByUsername(p)));
    }

    public void DeleteStudent(long idClassroom, Long idStudent) {
        ClassRoom classRoom = classRepository.getById(idClassroom);
        User user = userRepository.getById(idStudent);

        classRoom.getUsers().forEach(e -> {
            if (e.getId().equals(user.getId())) {
                classRoom.getUsers().remove(e);
            }
        });
    }

    public void DeleteProf(long idClassroom, Long idProf) {
        ClassRoom classRoom = classRepository.getById(idClassroom);
        User user = userRepository.getById(idProf);

        classRoom.getUsers().forEach(e -> {
            if (e.getId().equals(user.getId())) {
                classRoom.getProfessors().remove(e);
            }
        });
    }

    public ResponseEntity<Collection<Course>> getCoursesByClassRoomID(Long id) {
        ClassRoom classRoom = classRepository.getById(id);
        return ResponseEntity.ok().body(classRoom.getCourses());
    }


}
