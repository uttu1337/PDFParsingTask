package com.example.pdfparsingtask.parser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.pdfparsingtask.constants.PdfConstants.*;

@Component
public class PdfParser {

    public Map<String, String> parsePdf(MultipartFile file) throws IOException {

        String text = getFullText(file);
        Map<String, String> result = new HashMap<>();

        result.put(FILE_EXTENSION_FIELD, getExtension(file));
        result.put(FILE_SIZE_FIELD, getSize(file));
        putFields(result, text);

        return result;
    }

    public String getFullText(MultipartFile file) throws IOException {

        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();

            return stripper.getText(document);
        }
    }

    private String getExtension(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        int dotPosition = fileName.lastIndexOf(DOT);

        return fileName.substring(dotPosition + 1);
    }

    private String getSize(MultipartFile file) {

        long size = file.getSize();

        return Long.toString(size);
    }

    private void putFields(Map<String, String> result, String text) {

        String[] lines = text.split(LINE_BREAK_REGEX);

        for (String s : lines) {
            String line = s.trim();

            if (line.contains(NAME_SUBSTRING) && line.contains(PATRONYMIC_SUBSTRING)) {

                putName(result, line);
            }

            if (line.contains(BIRTH_DATE_SUBSTRING)) {

                putBirthDate(result, line);
            }

            if (line.startsWith(TAXPAYER_IDENTIFICATION_NUMBER_SUBSTRING)) {

                putTaxpayerIdentificationNumber(result, line);
            }

            if (line.startsWith(DOCUMENT_TYPE_SUBSTRING)) {

                putDocumentTypeSeriesAndNumber(result, line);
            }

            if (line.startsWith(CERTIFICATE_SUBSTRING)) {

                putESignatureValue(result, line);
            }

            if (line.startsWith(OWNER_SUBSTRING)) {

                putESignatureOwner(result, line);
            }

            if (line.startsWith(PRODUCTION_DATE_SUBSTRING)) {

                putProductionAndExpirationDate(result, line);
            }
        }
    }

    private void putName(Map<String, String> result, String line) {
        String[] parts = line.split(SPACE);

        result.put(LAST_NAME_FIELD, parts[1]);
        result.put(FIRST_NAME_FIELD, parts[3]);

        if (parts.length > 4) {

            result.put(PATRONYMIC_FIELD, parts[6]); // 6 чтоб скипнуть "*"
        }

    }

    private void putBirthDate(Map<String, String> result, String line) {

        int start = line.indexOf(BIRTH_DATE_SUBSTRING) + BIRTH_DATE_SUBSTRING.length();
        int end = line.indexOf(CITIZENSHIP_SUBSTRING);

        if (start > 0 && end > start) {

            String birthDateRaw = line.substring(start, end).replaceAll(WHITESPACE_REGEX, NOTHING);
            result.put(BIRTH_DATE_FIELD, birthDateRaw);
        }
    }

    private void putTaxpayerIdentificationNumber(Map<String, String> result, String line) {

        result.put(TAXPAYER_IDENTIFICATION_NUMBER_FIELD, line.replace(TAXPAYER_IDENTIFICATION_NUMBER_SUBSTRING + SPACE, NOTHING).trim());
    }

    private void putDocumentTypeSeriesAndNumber(Map<String, String> result, String line) {

        String[] partsOfDocument = line.replace(DOCUMENT_TYPE_SUBSTRING + COLON, NOTHING)
                .split(DOCUMENT_SERIES_AND_NUMBER_SUBSTRING + SPACE);

        if (partsOfDocument.length == 2) {

            result.put(DOCUMENT_TYPE_FIELD, partsOfDocument[0].trim());
            result.put(SERIES_AND_NUMBER_FIELD, partsOfDocument[1].replaceAll(WHITESPACE_REGEX, NOTHING).trim());
        }
    }

    private void putESignatureValue(Map<String, String> result, String line) {

        result.put(E_SIGNATURE_VALUE_FIELD, line.replace(CERTIFICATE_SUBSTRING + COLON + SPACE, NOTHING).trim());
    }

    private void putESignatureOwner(Map<String, String> result, String line) {

        result.put(E_SIGNATURE_OWNER_FIELD, line.replace(OWNER_SUBSTRING + COLON + SPACE, NOTHING).trim());
    }

    private void putProductionAndExpirationDate(Map<String, String> result, String line) {
        String[] partsOfDates = line.replace(PRODUCTION_DATE_SUBSTRING, NOTHING)
                .split(UNTIL_SUBSTRING);

        if (partsOfDates.length == 2) {

            result.put(PRODUCTION_DATE_FIELD, partsOfDates[0].trim());
            result.put(EXPIRATION_DATE_FIELD, partsOfDates[1].trim());
        }
    }
}
