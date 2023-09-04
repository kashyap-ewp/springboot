package com.example.department.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto {
    private boolean status;
    private String message;
    private Object data;
}
