package com.emsi.pfa.elearning.service;

import com.emsi.pfa.elearning.dao.*;
import com.emsi.pfa.elearning.model.*;
import com.emsi.pfa.elearning.model.ClassRoom;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class testserv implements CommandLineRunner, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PostRepository postRepository;
    private final CourseRepository courseRepository;
    private final CommentRepository commentRepository;
    private final ClassRepository classRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ExamRepository examRepository;
    private final DevoirRepository devoirRepository;
    private final QuestionRepository questionRepository;
    private final ToDoListRepository toDoRepository;
    private final EventRepository eventRepository;

    void inituser(){
        User u=new User();
        u.setEmail("Etudiant1Email@gmail.com");
        u.setUsername("Etudiant1");
        u.setFirstName("Mohamed");
        u.setPassword(bCryptPasswordEncoder.encode("password"));
        u.setLastName("Alami");
        u.getRoles().add(roleRepository.findByName("STUDENT"));
        userRepository.save(u);

        User u4=new User();
        u4.setEmail("Etudiant2Email@gmail.com");
        u4.setUsername("Etudiant2");
        u4.setFirstName("Ayoub");
        u4.setPassword(bCryptPasswordEncoder.encode("password"));
        u4.setLastName("Terari");
        u4.getRoles().add(roleRepository.findByName("STUDENT"));
        userRepository.save(u4);

        User u5=new User();
        u5.setEmail("Etudiant3Email@gmail.com");
        u5.setUsername("Etudiant3");
        u5.setFirstName("Raiss");
        u5.setPassword(bCryptPasswordEncoder.encode("password"));
        u5.setLastName("Moundir");
        u5.getRoles().add(roleRepository.findByName("STUDENT"));
        userRepository.save(u5);

        User u2=new User();
        u2.setEmail("Prof1Email@gmail.com");
        u2.setUsername("Professor");
        u2.setFirstName("professor");
        u2.setPassword(bCryptPasswordEncoder.encode("password"));
        u2.setLastName("test");
        u2.getRoles().add(roleRepository.findByName("PROFESSOR"));
        userRepository.save(u2);

        User u7=new User();
        u7.setEmail("Prof2Email@gmail.com");
        u7.setUsername("Professor2");
        u7.setFirstName("Mohamed");
        u7.setPassword(bCryptPasswordEncoder.encode("password"));
        u7.setLastName("Alaoui");
        u7.getRoles().add(roleRepository.findByName("PROFESSOR"));
        userRepository.save(u7);

        User u3=new User();
        u3.setEmail("Admin1Email@gmail.com");
        u3.setUsername("admin");
        u3.setFirstName("admin");
        u3.setPassword(bCryptPasswordEncoder.encode("password"));
        u3.setLastName("junior");
        u3.getRoles().add(roleRepository.findByName("ADMIN"));
        userRepository.save(u3);
    }

    void initClassroom(){
        ClassRoom classroom=new ClassRoom();
        classroom.setName("5IIR1");
        classroom.getProfessors().add(userRepository.findByUsername("Professor"));
        classroom.getUsers().add(userRepository.findByUsername("Etudiant1"));
        classroom.getUsers().add(userRepository.findByUsername("Etudiant2"));
        classRepository.save(classroom);

        ClassRoom classroom2=new ClassRoom();
        classroom2.setName("5IIR2");
        classroom2.getProfessors().add(userRepository.findByUsername("Professor1"));
        classroom2.getUsers().add(userRepository.findByUsername("Etudiant3"));
        classRepository.save(classroom2);
    }



    public void initToDo(){
        ToDoList t=new ToDoList();
        t.setDescription("To do list for user ");
        t.setType("Done");
        t.setUser(userRepository.findByUsername("admin"));
        t.setTitle("Done");

        ToDoList t4=new ToDoList();
        t4.setDescription("Test To do");
        t4.setType("Normal");
        t4.setUser(userRepository.findByUsername("admin"));
        t4.setTitle("Normal");

        ToDoList t2=new ToDoList();
        t2.setDescription("Test To do");
        t2.setType("Trash");
        t2.setUser(userRepository.findByUsername("admin"));
        t2.setTitle("Trash");

        ToDoList t3=new ToDoList();
        t3.setDescription("Test ihahahTo do");
        t3.setType("Important");
        t3.setUser(userRepository.findByUsername("admin"));
        t3.setTitle("Important");

        toDoRepository.save(t);
        toDoRepository.save(t2);
        toDoRepository.save(t3);
        toDoRepository.save(t4);

    }

    public void initEvent(){

        Event b = new Event();
        b.setDescription("Event for testing purposes");
        b.setTitle("Event1");
        b.setEventUser(userRepository.findByUsername("admin"));
        b.setStartDate(LocalDateTime.now());
        b.setEndDate(LocalDateTime.now().plusDays(2));
        eventRepository.save(b);

    }
    void initRole(){
        Role R = new Role();
        Role U = new Role();
        Role R3 = new Role();
        R.setName("ADMIN");
        R3.setName("PROFESSOR");
        U.setName("STUDENT");
        roleRepository.save(R);
        roleRepository.save(R3);
        roleRepository.save(U);

    }
    void initDevoir(){
        Devoir devoir = new Devoir();
        devoir.setName("S1 devoir 1");
        devoirRepository.save(devoir);
       Question question = new Question();
       question.setName("JAVA is an object oriented language");
       question.setReponses(Arrays.asList("True" , "False"));
       question.setReponsesCorrect(List.of(question.getReponses().get(1)));
       question.setDevoir(devoir);
       questionRepository.save(question);

        //devoir.setQuestions(Collections.singletonList(question));




    }
    void initExams(){
        Exam exam=new Exam();
        exam.setDuration(120L);
        exam.setName("Examin 1");
        exam.setCourseExam(courseRepository.getById(1L));
        exam.setStartDate(LocalDateTime.now().plusDays(10));
        exam.setEndDate(exam.getStartDate().plusMinutes(exam.getDuration()));
        if(exam.getEndDate().isAfter(LocalDateTime.now()) || exam.getStartDate().isBefore(LocalDateTime.now())){
            exam.setIsActive(false);
        }
        examRepository.save(exam);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Username not found in database !");
        }
        Collection<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),user.getEnabled(),true,true,true,authorities);
    }

    void initCourse(){
        Course R = new Course();
        R.setName("JAVA OOP 2");
        R.setClassroom(classRepository.findByName("5IIR1"));
        R.setProfessor(userRepository.findByUsername("Professor"));
        R.getUsers().add(userRepository.findByUsername("Etudiant1"));
        R.setCourseCode(UUID.randomUUID().toString().replaceAll("-", "").trim().substring(0,6));
        courseRepository.save(R);

        Course R2 = new Course();
        R2.setName("Data Mining");
        R2.setClassroom(classRepository.findByName("5IIR1"));
        R2.setProfessor(userRepository.findByUsername("Professor2"));
        R2.setCourseCode(UUID.randomUUID().toString().replaceAll("-", "").trim().substring(0,6));
        courseRepository.save(R2);

    }

    void initPosts(){
        Post R=new Post();
        R.setCourse(courseRepository.getById(1L));
        R.setTitle("Java introduction");
        R.setDescription("Java is an object oriented programming language");
        R.setUser(userRepository.findByUsername("Professor"));
        postRepository.save(R);
    }

    void initComments(){
        Comment C=new Comment();
        C.setCommentUser(userRepository.findByUsername("Etudiant1"));
        C.setText("Thanks for the valuable information");
        C.setPost(postRepository.getById(1L));
        commentRepository.save(C);

        Comment C2=new Comment();
        C2.setCommentUser(userRepository.findByUsername("Etudiant2"));
        C2.setText("I have a problem and would like to discuss more details in private if it's possible,thank you");
        C2.setPost(postRepository.getById(1L));
        commentRepository.save(C2);
    }

    @Override
    public void run(String... args) throws Exception {
        initRole();
        inituser();
        initClassroom();
        initCourse();
        initPosts();
        initComments();
        initExams();
        initDevoir();
        initToDo();
        initEvent();
    }
}
