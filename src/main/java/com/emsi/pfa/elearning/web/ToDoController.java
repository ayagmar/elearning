package com.emsi.pfa.elearning.web;

import com.emsi.pfa.elearning.model.ToDoList;
import com.emsi.pfa.elearning.service.ToDoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/todos")
public class ToDoController {
    private final ToDoService toDoService;

    @ApiOperation(value = "Used by Logged in client to get his to do list by type")
    @GetMapping("/byType/{type}")
    public ResponseEntity<List<ToDoList>> getUserTodos(@PathVariable String type) {
        return toDoService.getTodos(type);
    }

    @ApiOperation(value = "Used by Logged in client to get his to do list by type")
    @GetMapping("/all")
    public ResponseEntity<List<ToDoList>> getAllTodos() {
        return toDoService.getAllTodos();
    }

    @ApiOperation(value = "Used by Logged in client to delete his to do ")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        return toDoService.deleteTodo(id);
    }

    @ApiOperation(value = "Used by Logged in client to change his to do list type ")
    @PutMapping("/{id}/{type}")
    public ResponseEntity<String> setTodoType(@PathVariable Long id, @PathVariable String type) {
        return toDoService.setType(id, type);
    }

    @PostMapping("/create")
    public ResponseEntity<String> CreateTodo(@RequestBody ToDoList toDoDTO) {
        return toDoService.createTodo(toDoDTO);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<String> updateTodo(@RequestBody ToDoList toDoDTO, @PathVariable Long id) {
        return toDoService.updateTodo(id, toDoDTO);
    }


}

