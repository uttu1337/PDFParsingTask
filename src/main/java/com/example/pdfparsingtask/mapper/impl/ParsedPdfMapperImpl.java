package com.example.pdfparsingtask.mapper.impl;

import com.example.pdfparsingtask.dto.ParsedPdfDto;
import com.example.pdfparsingtask.mapper.ParsedPdfMapper;
import com.example.pdfparsingtask.model.ParsedPdfModel;
import com.example.pdfparsingtask.repository.entity.ParsedPdfEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.example.pdfparsingtask.constants.DateTimeConstants.DATE_FORMAT;
import static com.example.pdfparsingtask.constants.PdfConstants.*;

@Component
public class ParsedPdfMapperImpl implements ParsedPdfMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    @Override
    public ParsedPdfModel mapToModel(Map<String, String> map) {

        return ParsedPdfModel.builder()
                .fileSize(Long.parseLong(map.get(FILE_SIZE_FIELD)))
                .fileExtension(map.get(FILE_EXTENSION_FIELD))
                .firstName(map.get(FIRST_NAME_FIELD))
                .lastName(map.get(LAST_NAME_FIELD))
                .patronymic(map.get(PATRONYMIC_FIELD))
                .birthDate(LocalDate.parse(clearDate(map.get(BIRTH_DATE_FIELD)), formatter))
                .taxpayerIdentificationNumber(map.get(TAXPAYER_IDENTIFICATION_NUMBER_FIELD))
                .documentType(Integer.parseInt(map.get(DOCUMENT_TYPE_FIELD)))
                .seriesAndNumber(Long.parseLong(map.get(SERIES_AND_NUMBER_FIELD)))
                .eSignatureValue(map.get(E_SIGNATURE_VALUE_FIELD))
                .eSignatureOwner(map.get(E_SIGNATURE_OWNER_FIELD))
                .productionDate(LocalDate.parse(clearDate(map.get(PRODUCTION_DATE_FIELD)), formatter))
                .expirationDate(LocalDate.parse(clearDate(map.get(EXPIRATION_DATE_FIELD)), formatter))
                .build();

    }

    @Override
    public ParsedPdfDto modelToDto(ParsedPdfModel pdfModel) {

        return ParsedPdfDto.builder()
                .fileSize(pdfModel.getFileSize())
                .fileExtension(pdfModel.getFileExtension())
                .firstName(pdfModel.getFirstName())
                .lastName(pdfModel.getLastName())
                .patronymic(pdfModel.getPatronymic())
                .birthDate(pdfModel.getBirthDate())
                .taxpayerIdentificationNumber(pdfModel.getTaxpayerIdentificationNumber())
                .documentType(pdfModel.getDocumentType())
                .seriesAndNumber(pdfModel.getSeriesAndNumber())
                .eSignatureValue(pdfModel.getESignatureValue())
                .eSignatureOwner(pdfModel.getESignatureOwner())
                .productionDate(pdfModel.getProductionDate())
                .expirationDate(pdfModel.getExpirationDate())
                .build();
    }

    @Override
    public ParsedPdfEntity modelToEntity(ParsedPdfModel model) {

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

    @Override
    public ParsedPdfModel entityToModel(ParsedPdfEntity entity) {

        return ParsedPdfModel.builder()
                .fileSize(entity.getFileSize())
                .fileExtension(entity.getFileExtension())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .patronymic(entity.getPatronymic())
                .birthDate(entity.getBirthDate())
                .taxpayerIdentificationNumber(entity.getTaxpayerIdentificationNumber())
                .documentType(entity.getDocumentType())
                .seriesAndNumber(entity.getSeriesAndNumber())
                .eSignatureValue(entity.getESignatureValue())
                .eSignatureOwner(entity.getESignatureOwner())
                .productionDate(entity.getProductionDate())
                .expirationDate(entity.getExpirationDate())
                .build();
    }
    private static String clearDate(String raw) {

        return raw == null ? null : raw.replaceAll("[^0-9.]", "");
    }
}
