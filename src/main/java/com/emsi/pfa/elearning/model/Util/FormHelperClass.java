package com.emsi.pfa.elearning.model.Util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class FormHelperClass {

    @Data
    public static class formClass{
        public String name;
        public List<String> students=new ArrayList<>();
        public List<String> professors=new ArrayList<>();
    }

    @Data
    public static class formCourse{
        public String courseName;
        public String professorName;
    }

}
