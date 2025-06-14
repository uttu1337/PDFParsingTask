package com.example.pdfparsingtask.exception.handler;

import com.example.pdfparsingtask.exception.PdfParsingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PdfParsingException.class)
    public ResponseEntity<?> handlePdfParsingException(PdfParsingException e) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("400 Bad Request:" + e.getMessage());
    }
}
