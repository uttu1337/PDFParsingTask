package com.example.pdfparsingtask.mapper;

import com.example.pdfparsingtask.dto.ParsedPdfDto;
import com.example.pdfparsingtask.model.ParsedPdfModel;
import com.example.pdfparsingtask.repository.entity.ParsedPdfEntity;

import java.util.Map;

public interface ParsedPdfMapper {

    ParsedPdfModel mapToModel(Map<String, String> map);

    ParsedPdfDto modelToDto(ParsedPdfModel pdfModel);

    ParsedPdfEntity modelToEntity(ParsedPdfModel model);

    ParsedPdfModel entityToModel(ParsedPdfEntity entity);
}
