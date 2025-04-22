package com.example.pdfboxandshit.controller;

import com.example.pdfboxandshit.dto.ParsePdfDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.pdfboxandshit.service.PdfService;

import java.io.IOException;
import java.util.Map;

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

    @PostMapping("/retrieveInfo")
    public ResponseEntity<String> retrieveType(@RequestParam("file") MultipartFile file) {
        String text = pdfService.retrieveType(file);

        return ResponseEntity.ok(text);
    }

    @PostMapping("/retrieveSize")
    public ResponseEntity<String> retrieveSize(@RequestParam("file") MultipartFile file) {
        String size = pdfService.retrieveSize(file);

        return ResponseEntity.ok(size);
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

            System.out.println("Oshibka blyat!!");

            return null;
        }
    }
}
