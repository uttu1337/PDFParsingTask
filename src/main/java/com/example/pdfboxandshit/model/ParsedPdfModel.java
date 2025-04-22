package com.example.pdfboxandshit.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class ParsedPdfModel {

    private UUID id;

    private String lastName;
    private String firstName;
    private String patronymic;

    private LocalDate birthDate;
    private String taxpayerIdentificationNumber;
    private int documentType;
    private int seriesAndNumber;

    private String eSignatureValue;
    private String eSignatureOwner;
    private LocalDate productionDate;
    private LocalDate expirationDate;
}
