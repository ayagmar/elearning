package com.emsi.pfa.elearning.web;

import com.emsi.pfa.elearning.model.Course;
import com.emsi.pfa.elearning.model.Util.FormHelperClass;
import com.emsi.pfa.elearning.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        return courseService.getAll();
    }

    @GetMapping("/count/{name}")
    public ResponseEntity<Object> getCourseNumberByClassName(@PathVariable String name) {
        return courseService.getCourseNumberByClassRoomID(name);
    }

    @ApiOperation(value = "Create a course")
    @PostMapping("/create")
    public void create(@RequestBody FormHelperClass.formCourse form) {
        courseService.CreateCourse(form);
    }
}
