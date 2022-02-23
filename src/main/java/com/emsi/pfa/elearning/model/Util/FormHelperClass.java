package com.emsi.pfa.elearning.model.Util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class FormHelperClass {

    @Data
    public static class formClass {
        public String name;
        private Integer category;
        private String branch;
        public List<String> students = new ArrayList<>();
        public List<String> professors = new ArrayList<>();
    }


    @Data
    public static class UserEventForm {
        private String dateStart;
        private String dateEnd;
        private String description;
        private String title;
        private String username;
    }


    @Data
    public static class postForm {
        public Long courseID;
        public String postTitle;
        public String description;
    }

    @Data
    public static class formCourse {
        public String courseName;
        public String professorName;
        public Long classroomID;
    }


    @Data
    public static class formExam {
        public Long courseID;
        public String examName;
        public Long hours;
        public String startDate;
    }

}
