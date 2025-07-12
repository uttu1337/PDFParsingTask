package com.example.pdfparsingtask.service;

import com.example.pdfparsingtask.dto.ParsedPdfDto;
import org.springframework.web.multipart.MultipartFile;

public interface PdfService {

    String getFullText(MultipartFile file);

    ParsedPdfDto parsePdf(MultipartFile file);
}
