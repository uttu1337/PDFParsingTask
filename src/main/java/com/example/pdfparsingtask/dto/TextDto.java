package com.example.pdfparsingtask.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;

@Data
public class TextDto {

    @JsonRawValue
    private String text;
}
