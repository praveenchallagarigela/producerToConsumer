package com.praveentechie.producertoconsumer.controller;

import com.praveentechie.producertoconsumer.dto.ProductDto;
import com.praveentechie.producertoconsumer.exceptions.ProductNotFoundException;
import com.praveentechie.producertoconsumer.model.Product;
import com.praveentechie.producertoconsumer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/save")
    //@PreAuthorize(value = "Admin")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductDto productDto) throws ProductNotFoundException{
        return new ResponseEntity<>(productService.saveProduct(productDto),HttpStatus.CREATED);
    }
    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProduct(@PathVariable("product_id") Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getProduct(id));
    }
}
