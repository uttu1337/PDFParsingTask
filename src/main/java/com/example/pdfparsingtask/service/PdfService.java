package com.example.pdfparsingtask.service;

import com.example.pdfparsingtask.dto.ParsedPdfDto;
import com.example.pdfparsingtask.dto.TextDto;
import com.example.pdfparsingtask.model.ParsedPdfModel;
import org.springframework.web.multipart.MultipartFile;

public interface PdfService {

    TextDto getFullText(MultipartFile file);

    ParsedPdfDto parsePdf(MultipartFile file);

    ParsedPdfModel save(ParsedPdfModel model);
}
