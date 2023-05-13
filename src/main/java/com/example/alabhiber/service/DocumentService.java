package com.example.alabhiber.service;

import com.example.alabhiber.db.entity.Document;

import java.util.List;

public interface DocumentService {
    List<Document> findAllByActiveDocNumLike(String pattern);
}
