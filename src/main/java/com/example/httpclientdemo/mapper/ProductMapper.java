package com.example.httpclientdemo.mapper;

import com.example.httpclientdemo.models.Product;
import com.example.httpclientdemo.models.ProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);

    List<Product> toProducts(List<ProductDTO> productDTOS);
    List<ProductDTO> toProductDTOs(List<Product> products);

    Product toProduct(ProductDTO productDTO);
}
