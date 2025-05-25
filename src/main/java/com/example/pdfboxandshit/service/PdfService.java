package com.example.pdfboxandshit.service;

import com.example.pdfboxandshit.dto.ParsePdfDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PdfService {

    String getFullText(MultipartFile file) throws IOException;

    ParsePdfDto parsePdf(MultipartFile file) throws IOException;
}
