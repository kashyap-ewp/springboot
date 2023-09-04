package com.example.demo.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    @NonNull
    private Integer id;
    private String name;
}
