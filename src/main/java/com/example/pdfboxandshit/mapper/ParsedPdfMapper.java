package com.example.pdfboxandshit.mapper;

import com.example.pdfboxandshit.dto.ParsePdfDto;
import com.example.pdfboxandshit.model.ParsedPdfModel;
import com.example.pdfboxandshit.repository.entity.ParsedPdfEntity;

import java.util.Map;

public interface ParsedPdfMapper {

    ParsePdfDto MapToDto(Map<String, String> map);

    ParsedPdfModel DtoToModel(ParsePdfDto dto);

    ParsedPdfEntity ModelToEntity(ParsedPdfModel model);
}
