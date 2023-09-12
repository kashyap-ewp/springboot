package com.example.demo.mapper;

import com.example.demo.models.Product;
import com.example.demo.models.ProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);

    List<Product> toProducts(List<ProductDTO> productDTOS);
    List<ProductDTO> toProductDTOs(List<Product> products);

    Product toProduct(ProductDTO productDTO);
}
