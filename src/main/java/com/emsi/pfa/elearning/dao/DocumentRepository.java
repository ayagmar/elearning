package com.emsi.pfa.elearning.dao;

import com.emsi.pfa.elearning.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DocumentRepository extends JpaRepository<Document, Long> {
}