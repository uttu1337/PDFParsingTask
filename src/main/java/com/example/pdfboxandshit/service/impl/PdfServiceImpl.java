package com.example.pdfboxandshit.service.impl;

import com.example.pdfboxandshit.dto.ParsePdfDto;
import com.example.pdfboxandshit.parser.PdfParser;
import lombok.RequiredArgsConstructor;
import com.example.pdfboxandshit.mapper.ParsedPdfMapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.pdfboxandshit.service.PdfService;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {

    @Autowired
    private PdfParser pdfParser;

    @Autowired
    private ParsedPdfMapper parsedPdfMapper;

    @Override
    public String getFullText(MultipartFile file) throws IOException {

        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();

            return stripper.getText(document);
        }
    }

    @Override
    public ParsePdfDto parsePdf(MultipartFile file) throws IOException {
        Map<String, String> parsedMap = pdfParser.parsePdf(file);

        return parsedPdfMapper.MapToDto(parsedMap);
    }
}
