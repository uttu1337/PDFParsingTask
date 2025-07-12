package com.example.pdfparsingtask.controller;

import com.example.pdfparsingtask.dto.ParsedPdfDto;
import com.example.pdfparsingtask.dto.TextDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.pdfparsingtask.service.PdfService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pdf")
public class PdfController {

    private final PdfService pdfService;

    @PostMapping("/getText")

    public TextDto getText(@RequestParam("file") MultipartFile file) {

        return pdfService.getFullText(file);
    }

    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    @ResponseBody
    public ParsedPdfDto parsePdf(@RequestParam("file") MultipartFile file) {

        return pdfService.parsePdf(file);
    }
}
