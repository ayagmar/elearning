package com.emsi.pfa.elearning.dao;

import com.emsi.pfa.elearning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findByRoles_Id(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}