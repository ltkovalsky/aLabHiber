package com.example.alabhiber.db.repository;

import com.example.alabhiber.db.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {

    List<Document> findAllByDocNumber(String docNumber);

    List<Document> findAllByArchivedIsFalseAndDocNumberContaining(String pattern);
}
