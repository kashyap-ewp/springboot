package com.example.httpclientdemo.models;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductDTO {
    @Id
    private int id;

    @NotNull(message = "{NotNull.name}")
    private String name;

    @Size(max = 100)
    private String description;

    @Min(1)
    private Integer price;
}
