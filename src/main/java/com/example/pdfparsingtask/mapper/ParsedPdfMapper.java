package com.example.pdfparsingtask.mapper;

import com.example.pdfparsingtask.dto.ParsePdfDto;
import com.example.pdfparsingtask.model.ParsedPdfModel;
import com.example.pdfparsingtask.repository.entity.ParsedPdfEntity;

import java.util.Map;

public interface ParsedPdfMapper {

    ParsePdfDto MapToDto(Map<String, String> map);

    ParsedPdfModel DtoToModel(ParsePdfDto dto);

    ParsedPdfEntity ModelToEntity(ParsedPdfModel model);
}
