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
    public String getText(@RequestParam("file") MultipartFile file) {
        TextDto textDto = new TextDto();
        textDto.setText(pdfService.getFullText(file));

        return textDto.getText();
    }

    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    @ResponseBody
    public ParsedPdfDto parsePdf(@RequestParam("file") MultipartFile file) {

        return pdfService.parsePdf(file);
    }
}
