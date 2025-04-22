package com.example.pdfboxandshit.mapper;

import com.example.pdfboxandshit.dto.ParsePdfDto;

import java.util.Map;

public interface ParsedPdfMapper {

    ParsePdfDto MapToDto(Map<String, String> map);
}
