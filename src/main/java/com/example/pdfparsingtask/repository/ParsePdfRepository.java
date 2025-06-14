package com.example.pdfparsingtask.repository;

import com.example.pdfparsingtask.repository.entity.ParsedPdfEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParsePdfRepository extends JpaRepository<ParsedPdfEntity, UUID> {

}
