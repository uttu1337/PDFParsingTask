package com.example.pdfboxandshit.controller;

import com.example.pdfboxandshit.dto.ParsePdfDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.pdfboxandshit.service.PdfService;

import java.io.IOException;
@RestController
@RequestMapping("/pdf")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/getText")
    public ResponseEntity<String> getText(@RequestParam("file") MultipartFile file) {
        try {
            String text = pdfService.getFullText(file);

            return ResponseEntity.ok(text);
        } catch (IOException e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while parsing PDF" + e.getMessage());
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {

        return ResponseEntity.ok("Test!");
    }

    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    @ResponseBody
    public ParsePdfDto parsePdf(@RequestParam("file") MultipartFile file) {

        try {

            return pdfService.parsePdf(file);
        } catch (IOException e) {

            System.out.println("Parsing error!");

            return null;
        }
    }
}
