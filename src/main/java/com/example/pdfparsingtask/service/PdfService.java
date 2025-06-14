package com.example.pdfparsingtask.service;

import com.example.pdfparsingtask.dto.ParsePdfDto;
import org.springframework.web.multipart.MultipartFile;

public interface PdfService {

    String getFullText(MultipartFile file);

    ParsePdfDto parsePdf(MultipartFile file);
}
