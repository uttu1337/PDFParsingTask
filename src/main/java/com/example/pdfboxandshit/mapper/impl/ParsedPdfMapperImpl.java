package com.example.pdfboxandshit.mapper.impl;

import com.example.pdfboxandshit.dto.ParsePdfDto;
import com.example.pdfboxandshit.mapper.ParsedPdfMapper;
import com.example.pdfboxandshit.model.ParsedPdfModel;
import com.example.pdfboxandshit.repository.entity.ParsedPdfEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class ParsedPdfMapperImpl implements ParsedPdfMapper {

    @Override
    public ParsePdfDto MapToDto(Map<String, String> map) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return ParsePdfDto.builder()
                .fileSize(Long.parseLong(map.get("fileSize")))
                .fileExtension(map.get("fileExtension"))
                .firstName(map.get("firstName"))
                .lastName(map.get("lastName"))
                .patronymic(map.get("patronymic"))
                .birthDate(LocalDate.parse(map.get("birthDate").replaceAll("[^0-9.]", ""), formatter))
                .taxpayerIdentificationNumber(map.get("taxpayerIdentificationNumber"))
                .documentType(Integer.parseInt(map.get("documentType")))
                .seriesAndNumber(Long.parseLong(map.get("seriesAndNumber")))
                .eSignatureValue(map.get("eSignatureValue"))
                .eSignatureOwner(map.get("eSignatureOwner"))
                .productionDate(LocalDate.parse(map.get("productionDate").replaceAll("[^0-9.]", ""), formatter))
                .expirationDate(LocalDate.parse(map.get("expirationDate").replaceAll("[^0-9.]", ""), formatter))
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
}
