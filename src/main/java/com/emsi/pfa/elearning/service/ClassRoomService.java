package com.emsi.pfa.elearning.service;

import com.emsi.pfa.elearning.dao.*;
import com.emsi.pfa.elearning.model.ClassRoom;
import com.emsi.pfa.elearning.model.User;
import com.emsi.pfa.elearning.model.Util.FormHelperClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    private void DeleteClassroom(Long id ){
      classRepository.deleteById(id);
    }

    private void AddStudent( Long id , FormHelperClass.formClass form){
     ClassRoom classRoom=  classRepository.getById(id);
       form.getStudents().forEach(e -> classRoom.getUsers().add(userRepository.findByUsername(e)));
    }

    private void AddProfessor( Long id , FormHelperClass.formClass form){
        ClassRoom classRoom=  classRepository.getById(id);
        form.getProfessors().forEach(p -> classRoom.getProfessors().add(userRepository.findByUsername(p)));
    }

    private void DeleteStudent(long idClassroom , Long idStudent){
        ClassRoom classRoom=  classRepository.getById(idClassroom);
        User user = userRepository.getById(idStudent);

        classRoom.getUsers().forEach(e ->{
            if(e.getId().equals(user.getId())){
                classRoom.getUsers().remove(e);
        }
        });
    }
    private void DeleteProf(long idClassroom , Long idProf){
        ClassRoom classRoom=  classRepository.getById(idClassroom);
        User user = userRepository.getById(idProf);

        classRoom.getUsers().forEach(e ->{
            if(e.getId().equals(user.getId())){
                classRoom.getProfessors().remove(e);
            }
        });
    }


}
