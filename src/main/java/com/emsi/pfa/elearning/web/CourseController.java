package com.emsi.pfa.elearning.web;

import com.emsi.pfa.elearning.model.Util.FormHelperClass;
import com.emsi.pfa.elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses/")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("count/{name}")
    public ResponseEntity<Object> getCourseNumberByClassName(@PathVariable String name){
        return courseService.getCourseNumberByClassRoomID(name);
    }
}
