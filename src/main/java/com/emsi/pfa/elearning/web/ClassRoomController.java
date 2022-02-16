package com.emsi.pfa.elearning.web;

import com.emsi.pfa.elearning.model.Course;
import com.emsi.pfa.elearning.model.Util.FormHelperClass;
import com.emsi.pfa.elearning.service.ClassRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/classrooms")
@Api(value = "ClassRoom operations", description = "Operations pertaining to Class rooms  in E learning Web Application")
public class ClassRoomController {

    @Autowired
    private ClassRoomService classRoomService;

    @ApiOperation(value = "Create a classroom")
    @PostMapping("/create")
    public void create(@RequestBody FormHelperClass.formClass form){
        classRoomService.CreateClassroom(form);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getClassCount(){
        return classRoomService.getClassRoomsCount();
    }

    @DeleteMapping("/{id}")
    public void DeleteClassroom(@PathVariable Long id){
        classRoomService.DeleteClassroom(id);
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<Collection<Course>> getCoursesByClassID(@PathVariable Long id){
        return classRoomService.getCoursesByClassRoomID(id);
    }

    @PostMapping("/{id}/addStudents")
    public void AddStudentToClassRoom(@PathVariable Long id,@RequestBody FormHelperClass.formClass form){
        classRoomService.AddStudent(id, form);
    }

    @PostMapping("/{id}/addProfessors")
    public void AddProfessorToClassRoom(@PathVariable Long id,@RequestBody FormHelperClass.formClass form){
        classRoomService.AddProfessor(id, form);
    }

    @DeleteMapping("/{idClass}/students/{idStudent}")
    public void DeleteStudentFromClassRoom(@PathVariable Long idClass,@PathVariable Long idStudent){
        classRoomService.DeleteStudent(idClass,idStudent);
    }

    @DeleteMapping("/{idClass}/professor/{idProf}")
    public void DeleteProfessorFromClassRoom(@PathVariable Long idClass,@PathVariable Long idProf){
        classRoomService.DeleteProf(idClass,idProf);
    }



}
