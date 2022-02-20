package com.emsi.pfa.elearning.dao;

import com.emsi.pfa.elearning.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface ClassRepository extends JpaRepository<ClassRoom, Long> {
    ClassRoom findByName(String name);

    Optional<ClassRoom> findClassRoomByName(String name);
}