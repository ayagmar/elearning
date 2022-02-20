package com.emsi.pfa.elearning.service;

import com.emsi.pfa.elearning.dao.ToDoListRepository;
import com.emsi.pfa.elearning.dao.UserRepository;
import com.emsi.pfa.elearning.model.ToDoList;
import com.emsi.pfa.elearning.model.User;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class ToDoService {
    private final UserRepository userRepository;
    private final ToDoListRepository toDoRepository;
    public static List<String> types = Arrays.asList("Important", "Done", "Trash","Normal");

    public ResponseEntity<List<ToDoList>> getAllTodos() {
        return  ResponseEntity.ok().body(toDoRepository.findAll());
    }


    public ResponseEntity<List<ToDoList>> getTodos(String type) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        if (Objects.equals(type, "All")) {
            return ResponseEntity.ok().body(toDoRepository.findByUserId(LoggedInUser.getId()));
        }
        return ResponseEntity.ok().body(toDoRepository.findByUserIdAndType(LoggedInUser.getId(), type));
    }

    public ResponseEntity<String> setType(Long id, String type) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        Collection<ToDoList> toDos = LoggedInUser.getToDos();
        Optional<ToDoList> todo = toDoRepository.findById(id);


        if (todo.isPresent() && toDos.contains(todo.get()) && types.contains(type)) {
            todo.get().setType(type);
            toDoRepository.flush();
            return ResponseEntity.ok().body("Changed type successfully");
        }

        return ResponseEntity.badRequest().body("To do is not found or to do is not yours or type is not correct ! ");


    }

    public ResponseEntity<String> deleteTodo(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        Collection<ToDoList> toDos = LoggedInUser.getToDos();
        Optional<ToDoList> todo = toDoRepository.findById(id);
        if (todo.isPresent() && toDos.contains(todo.get())) {
            toDoRepository.deleteById(todo.get().getId());
            return ResponseEntity.ok().body("To do deleted successfully ");
        }
        return ResponseEntity.badRequest().body("To do not found or is not yours!");
    }

    public ResponseEntity<String> createTodo(ToDoList toDoDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        toDoDTO.setUser(LoggedInUser);
        toDoDTO.setType("Normal");
        if (types.contains(toDoDTO.getType())) {
            toDoRepository.save(toDoDTO);
            return ResponseEntity.ok().body("saved successfully");

        }
        return ResponseEntity.badRequest().body("please insert correct type");
    }

    public ResponseEntity<String> updateTodo(Long id,ToDoList newtodo){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User LoggedInUser = userRepository.findByUsername(auth.getPrincipal().toString());
        Collection<ToDoList> toDos = LoggedInUser.getToDos();
        Optional<ToDoList> todo = toDoRepository.findById(id);
        if (todo.isPresent() && toDos.contains(todo.get())){
            if(Objects.nonNull(newtodo.getTitle()) && !"".equalsIgnoreCase(newtodo.getTitle()))
                todo.get().setTitle(newtodo.getTitle());
            if(Objects.nonNull(newtodo.getDescription()) && !"".equalsIgnoreCase(newtodo.getDescription()))
                todo.get().setDescription(newtodo.getDescription());
            toDoRepository.flush();
            return ResponseEntity.ok().body("edited successfully");
        }
        return ResponseEntity.badRequest().body("Error editing todo!");
    }
}
