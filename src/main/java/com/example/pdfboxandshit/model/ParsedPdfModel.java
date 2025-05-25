package com.example.pdfboxandshit.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ParsedPdfModel {


    private Long fileSize;

    private String fileExtension;

    private String lastName;
    private String firstName;
    private String patronymic;
    private LocalDate birthDate;
    private String taxpayerIdentificationNumber;
    private Integer documentType;
    private Long seriesAndNumber;

    private String eSignatureValue;
    private String eSignatureOwner;
    private LocalDate productionDate;
    private LocalDate expirationDate;
}
