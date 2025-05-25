package com.example.pdfboxandshit.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@Table(name = "documents")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ParsedPdfEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
