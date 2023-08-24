package com.praveentechie.producertoconsumer.dto;

import com.praveentechie.producertoconsumer.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productName;
    private Long quantity;
    private UserDto userDto;
}
