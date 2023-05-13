package com.example.alabhiber.service.impl;

import com.example.alabhiber.db.entity.Document;
import com.example.alabhiber.db.repository.DocumentRepository;
import com.example.alabhiber.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceJPAImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public List<Document> findAllByActiveDocNumLike(String pattern) {
        return documentRepository.findAllByArchivedIsFalseAndDocNumberContaining(pattern);
    }
}
