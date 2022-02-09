package com.emsi.pfa.elearning.dao;

import com.emsi.pfa.elearning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findByRoles_Id(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}