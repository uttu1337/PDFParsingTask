package com.example.pdfparsingtask.service.impl;

import com.example.pdfparsingtask.dto.ParsePdfDto;
import com.example.pdfparsingtask.exception.PdfParsingException;
import com.example.pdfparsingtask.model.ParsedPdfModel;
import com.example.pdfparsingtask.parser.PdfParser;
import com.example.pdfparsingtask.repository.ParsePdfRepository;
import lombok.RequiredArgsConstructor;
import com.example.pdfparsingtask.mapper.ParsedPdfMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.pdfparsingtask.service.PdfService;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {

    private final PdfParser pdfParser;

    private final ParsedPdfMapper parsedPdfMapper;

    private final ParsePdfRepository parsePdfRepository;

    @Override
    public String getFullText(MultipartFile file) {
        try {

            return pdfParser.getFullText(file);
        } catch (IOException e) {
            throw new PdfParsingException("Encountered an exception while retrieving text: " + e.getMessage(), e);
        }
    }

    @Override
    public ParsePdfDto parsePdf(MultipartFile file) {
        try {

            Map<String, String> parsedMap = pdfParser.parsePdf(file);

            ParsePdfDto dto = parsedPdfMapper.MapToDto(parsedMap);
            ParsedPdfModel model = parsedPdfMapper.DtoToModel(dto);
            parsePdfRepository.save(parsedPdfMapper.ModelToEntity(model));

            return dto;
        } catch (IOException e) {
            throw new PdfParsingException("Encountered an exception while parsing: " + e.getMessage(), e);
        }
    }
}
