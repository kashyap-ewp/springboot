package com.example.demo.controllers;

import com.example.demo.mapper.ProductMapper;
import com.example.demo.models.Product;
import com.example.demo.models.ProductDTO;
import com.example.demo.models.ResponseDTO;
import com.example.demo.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductMapper productMapper;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insert(@RequestBody @Valid ProductDTO productDTO)
    {
        Product c = productService.insert(productMapper.toProduct(productDTO));

        if(c.getId() == 0)
        {
            ResponseDTO res = ResponseDTO.builder()
                            .status(false)
                            .message("Data not Saved, Please Enter Unique City Name")
                            .build();

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            ResponseDTO res = ResponseDTO.builder()
                    .status(true)
                    .message("Data Saved")
                    .data(productMapper.toProductDTO(c))
                    .build();

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updated(@RequestBody @Valid ProductDTO productDTO)
    {
        Product c = productService.insert(productMapper.toProduct(productDTO));

        if(c.getId() == 0)
        {
            ResponseDTO res = ResponseDTO.builder()
                    .status(false)
                    .message("Id does not exists")
                    .build();

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            ResponseDTO res = ResponseDTO.builder()
                    .status(true)
                    .message("Data Saved")
                    .data(productMapper.toProductDTO(c))
                    .build();

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @PostMapping("/insertAll")
    public ResponseEntity<ResponseDTO> insertAll(@RequestBody List<@Valid ProductDTO> productDTOS)
    {
        List<Product> cities = productService.insertMultiple(productMapper.toProducts(productDTOS));

        if(cities.isEmpty())
        {
            ResponseDTO res = ResponseDTO.builder()
                    .status(false)
                    .message("Data not Saved, Please Enter Unique City Name")
                    .build();

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            ResponseDTO res = ResponseDTO.builder()
                    .status(true)
                    .message("Data Saved")
                    .data(productMapper.toProductDTOs(cities))
                    .build();

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @GetMapping("/findAll")
    public ResponseEntity<ResponseDTO> findAll()
    {
        List<Product> cities = productService.findAll();

        if(cities.isEmpty())
        {
            ResponseDTO res = ResponseDTO.builder()
                    .status(false)
                    .message("No Data")
                    .build();

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            ResponseDTO res = ResponseDTO.builder()
                    .status(true)
                    .data(productMapper.toProductDTOs(cities))
                    .build();

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable int id)
    {
        Product product = productService.findById(id);

        if(product.getId() == 0)
        {
            ResponseDTO res = ResponseDTO.builder()
                    .status(false)
                    .message("Id not found")
                    .build();

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            ResponseDTO res = ResponseDTO.builder()
                    .status(true)
                    .data(productMapper.toProductDTO(product))
                    .build();

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable int id)
    {
        boolean isDeleted = productService.deleteById(id);

        if(!isDeleted)
        {
            ResponseDTO res = ResponseDTO.builder()
                    .status(false)
                    .message("Data not deleted")
                    .build();

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            ResponseDTO res = ResponseDTO.builder()
                    .status(true)
                    .message("Data deleted")
                    .build();

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
    @DeleteMapping("/deleteAllById")
    public ResponseEntity<ResponseDTO> deleteAllById(@RequestBody List<Integer> ids)
    {
        boolean isDeleted = productService.deleteAllById(ids);

        if(!isDeleted)
        {
            ResponseDTO res = ResponseDTO.builder()
                    .status(false)
                    .message("Data not deleted")
                    .build();

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            ResponseDTO res = ResponseDTO.builder()
                    .status(true)
                    .message("Data deleted")
                    .build();

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
}
