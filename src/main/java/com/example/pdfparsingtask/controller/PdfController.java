package com.example.pdfparsingtask.controller;

import com.example.pdfparsingtask.dto.ParsePdfDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.pdfparsingtask.service.PdfService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pdf")
public class PdfController {

    private final PdfService pdfService;

    @PostMapping("/getText")
    public ResponseEntity<String> getText(@RequestParam("file") MultipartFile file) {
        String text = pdfService.getFullText(file);

        return ResponseEntity.ok(text);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {

        return ResponseEntity.ok("Test!");
    }

    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    @ResponseBody
    public ParsePdfDto parsePdf(@RequestParam("file") MultipartFile file) {

        return pdfService.parsePdf(file);
    }
}
