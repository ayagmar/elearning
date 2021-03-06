package com.emsi.pfa.elearning.web;

import com.emsi.pfa.elearning.model.User;
import com.emsi.pfa.elearning.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getAllUsersCount() {
        return userService.getAllUsersCount();
    }

    @GetMapping("/role/{id}/count")
    public ResponseEntity<Integer> getAllUsersCountByRoleID(@PathVariable Long id) {
        return userService.getAllUsersCountByRoleId(id);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<List<User>> getAllUsersByRoleID(@PathVariable Long id) {
        return userService.getAllUsersByRoleId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        return userService.deleteUserByID(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }


    @ApiOperation(value = "Used by Admin to create another admin, a new professor or a new student (1=ADMIN) (2=Professor) (3=STUDENT) ")
    @PostMapping("/create/role/{id}")
    public void CreateUser(@RequestBody User user, @PathVariable Long id){
        userService.CreateUser(user,id);
    }


}
