package com.example.pdfparsingtask.parser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class PdfParser {

    public Map<String, String> parsePdf(MultipartFile file) throws IOException {

        String text = getFullText(file);
        Map<String, String> result = new HashMap<>();

        result.put("fileExtension", getExtension(file));
        result.put("fileSize", getSize(file));
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
        int dotPosition = fileName.lastIndexOf('.');

        return fileName.substring(dotPosition + 1);
    }

    private String getSize(MultipartFile file) {

        long size = file.getSize();

        return Long.toString(size);
    }

    private void putFields(Map<String, String> result, String text) {

        String[] lines = text.split("\\r?\\n");

        for (String s : lines) {
            String line = s.trim();

            if (line.contains("Имя ") && line.contains("Отчество")) {

                String[] parts = line.split(" ");

                result.put("lastName", parts[1]);
                result.put("firstName", parts[3]);

                if (parts.length > 4) {

                    result.put("patronymic", parts[6]); // 6 чтоб скипнуть "*"
                }

            }

            if (line.contains("Дата рождения")) {

                int start = line.indexOf("Дата рождения") + "Дата рождения".length();
                int end = line.indexOf("Гражданство");

                if (start > 0 && end > start) {

                    String birthDateRaw = line.substring(start, end).replaceAll("\\s", "");
                    result.put("birthDate", birthDateRaw);
                }
            }

            if (line.startsWith("ИНН в Российской Федерации")) {

                result.put("taxpayerIdentificationNumber", line.replace("ИНН в Российской Федерации ", "").trim());
            }

            if (line.startsWith("Код документа, удостоверяющего личность:")) {

                String[] partsOfDocument = line.replace("Код документа, удостоверяющего личность:", "")
                        .split("Серия и номер документа ");

                if (partsOfDocument.length == 2) {

                    result.put("documentType", partsOfDocument[0].trim());
                    result.put("seriesAndNumber", partsOfDocument[1].replaceAll("\\s", "").trim());
                }
            }

            if (line.startsWith("Сертификат:")) {

                result.put("eSignatureValue", line.replace("Сертификат: ", "").trim());
            }

            if (line.startsWith("Владелец:")) {

                result.put("eSignatureOwner", line.replace("Владелец: ", "").trim());
            }

            if (line.startsWith("Действителен: с")) {

                String[] partsOfDates = line.replace("Действителен: с", "")
                        .split("по");

                if (partsOfDates.length == 2) {

                    result.put("productionDate", partsOfDates[0].trim());
                    result.put("expirationDate", partsOfDates[1].trim());
                }
            }
        }
    }
}
