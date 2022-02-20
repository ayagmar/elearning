package com.emsi.pfa.elearning.web;

import com.emsi.pfa.elearning.model.Exam;
import com.emsi.pfa.elearning.model.ToDoList;
import com.emsi.pfa.elearning.model.Util.FormHelperClass;
import com.emsi.pfa.elearning.service.ExamService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/exams")
public class ExamController {
    @Autowired
    private ExamService examService;

    @PostMapping("/save")
    public ResponseEntity<String> saveDossier(@RequestPart FormHelperClass.formExam form, @RequestPart("document") List<MultipartFile> multipartFile) throws IOException {
        return examService.addExam(form,multipartFile);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Exam>> getAllExams() {
        return examService.getAllExams();
    }
    @GetMapping("/course/{id}")
    public ResponseEntity<Exam> getExamByCourseID(@PathVariable Long id) {
        return examService.getExamsByCourseID(id);
    }


}
