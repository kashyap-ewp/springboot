package com.example.demo.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class ResponseDTO<T> {
    private boolean status;
    private String message = "Success";
    private T data;

}
