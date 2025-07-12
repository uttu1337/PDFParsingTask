package com.example.pdfparsingtask.service.impl;

import com.example.pdfparsingtask.dto.ParsedPdfDto;
import com.example.pdfparsingtask.dto.TextDto;
import com.example.pdfparsingtask.exception.PdfParsingException;
import com.example.pdfparsingtask.model.ParsedPdfModel;
import com.example.pdfparsingtask.parser.PdfParser;
import com.example.pdfparsingtask.repository.ParsePdfRepository;
import com.example.pdfparsingtask.repository.entity.ParsedPdfEntity;
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
    public TextDto getFullText(MultipartFile file) {

        try {
            TextDto textDto = new TextDto();
            textDto.setText(pdfParser.getFullText(file));

            return textDto;
        } catch (IOException e) {
            throw new PdfParsingException("Encountered an exception while retrieving text: " + e.getMessage(), e);
        }
    }

    @Override
    public ParsedPdfDto parsePdf(MultipartFile file) {

        try {
            Map<String, String> parsedMap = pdfParser.parsePdf(file);
            ParsedPdfModel model = parsedPdfMapper.mapToModel(parsedMap);
            ParsedPdfModel savedModel = save(model);

            return parsedPdfMapper.modelToDto(savedModel);
        } catch (IOException e) {
            throw new PdfParsingException("Encountered an exception while parsing: " + e.getMessage(), e);
        }
    }

    @Override
    public ParsedPdfModel save(ParsedPdfModel model) {
        ParsedPdfEntity savedEntity = parsePdfRepository.save(parsedPdfMapper.modelToEntity(model));

        return parsedPdfMapper.entityToModel(savedEntity);
    }
}
