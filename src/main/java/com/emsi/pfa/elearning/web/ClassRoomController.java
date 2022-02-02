package com.emsi.pfa.elearning.web;

import com.emsi.pfa.elearning.model.Util.FormHelperClass;
import com.emsi.pfa.elearning.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassRoomController {

    @Autowired
    private ClassRoomService classRoomService;

    @PostMapping("/create")
    public void create(@RequestBody FormHelperClass.formClass form){
        classRoomService.CreateClassroom(form);
    }
}
