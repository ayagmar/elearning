package com.emsi.pfa.elearning.dao;

import com.emsi.pfa.elearning.model.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
    List<ToDoList> findByType(String type);

    List<ToDoList> findByUserIdAndType(Long id, String type);

    List<ToDoList> findByUserId(Long id);
}