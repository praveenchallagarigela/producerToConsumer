package com.praveentechie.producertoconsumer.service;

import com.praveentechie.producertoconsumer.dto.ProductDto;
import com.praveentechie.producertoconsumer.exceptions.ProductNotFoundException;
import com.praveentechie.producertoconsumer.model.Product;

public interface ProductService {
    Product saveProduct(ProductDto productDto)throws ProductNotFoundException;
    Product getProduct(Long productId) throws ProductNotFoundException;
}
