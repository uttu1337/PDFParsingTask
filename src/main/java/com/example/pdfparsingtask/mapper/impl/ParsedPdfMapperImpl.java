package com.example.pdfparsingtask.mapper.impl;

import com.example.pdfparsingtask.dto.ParsePdfDto;
import com.example.pdfparsingtask.mapper.ParsedPdfMapper;
import com.example.pdfparsingtask.model.ParsedPdfModel;
import com.example.pdfparsingtask.repository.entity.ParsedPdfEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class ParsedPdfMapperImpl implements ParsedPdfMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    @Override
    public ParsePdfDto MapToDto(Map<String, String> map) {

        return ParsePdfDto.builder()
                .fileSize(Long.parseLong(map.get("fileSize")))
                .fileExtension(map.get("fileExtension"))
                .firstName(map.get("firstName"))
                .lastName(map.get("lastName"))
                .patronymic(map.get("patronymic"))
                .birthDate(LocalDate.parse(clearDate(map.get("birthDate")), formatter))
                .taxpayerIdentificationNumber(map.get("taxpayerIdentificationNumber"))
                .documentType(Integer.parseInt(map.get("documentType")))
                .seriesAndNumber(Long.parseLong(map.get("seriesAndNumber")))
                .eSignatureValue(map.get("eSignatureValue"))
                .eSignatureOwner(map.get("eSignatureOwner"))
                .productionDate(LocalDate.parse(clearDate(map.get("productionDate")), formatter))
                .expirationDate(LocalDate.parse(clearDate(map.get("expirationDate")), formatter))
                .build();

    }

    @Override
    public ParsedPdfModel DtoToModel(ParsePdfDto dto) {

        return ParsedPdfModel.builder()
                .fileSize(dto.getFileSize())
                .fileExtension(dto.getFileExtension())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .patronymic(dto.getPatronymic())
                .birthDate(dto.getBirthDate())
                .taxpayerIdentificationNumber(dto.getTaxpayerIdentificationNumber())
                .documentType(dto.getDocumentType())
                .seriesAndNumber(dto.getSeriesAndNumber())
                .eSignatureValue(dto.getESignatureValue())
                .eSignatureOwner(dto.getESignatureOwner())
                .productionDate(dto.getProductionDate())
                .expirationDate(dto.getExpirationDate())
                .build();
    }

    @Override
    public ParsedPdfEntity ModelToEntity(ParsedPdfModel model) {

        return ParsedPdfEntity.builder()
                .fileSize(model.getFileSize())
                .fileExtension(model.getFileExtension())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .patronymic(model.getPatronymic())
                .birthDate(model.getBirthDate())
                .taxpayerIdentificationNumber(model.getTaxpayerIdentificationNumber())
                .documentType(model.getDocumentType())
                .seriesAndNumber(model.getSeriesAndNumber())
                .eSignatureValue(model.getESignatureValue())
                .eSignatureOwner(model.getESignatureOwner())
                .productionDate(model.getProductionDate())
                .expirationDate(model.getExpirationDate())
                .build();
    }

    private static String clearDate(String raw) {

        return raw == null ? null : raw.replaceAll("[^0-9.]", "");
    }
}
