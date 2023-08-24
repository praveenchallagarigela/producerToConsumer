package com.praveentechie.producertoconsumer.service;

import com.praveentechie.producertoconsumer.dto.ProductDto;
import com.praveentechie.producertoconsumer.dto.UserMapper;
import com.praveentechie.producertoconsumer.exceptions.ProductNotFoundException;
import com.praveentechie.producertoconsumer.model.Product;
import com.praveentechie.producertoconsumer.model.Role;
import com.praveentechie.producertoconsumer.model.User;
import com.praveentechie.producertoconsumer.repository.ProductRepository;
import com.praveentechie.producertoconsumer.repository.RoleRepository;
import com.praveentechie.producertoconsumer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Product saveProduct(ProductDto productDto) throws ProductNotFoundException {
        Product product = new Product();
        User user = new User();
        if (userRepository.findByEmail(productDto.getUserDto().getEmail()).isEmpty()) {
            user = userMapper.toEntity(productDto.getUserDto());
            List<Role> roles = new ArrayList<>();
            for (String roleName : productDto.getUserDto().getRoles()) {
                Role role = roleRepository.findByName(roleName);
                if (role == null) {
                    role = Role.builder().name(roleName).build();
                    roleRepository.save(role);
                }
                roles.add(role);
            }
            user.setRoles(roles);
        } else {
            user = userMapper.toEntity(productDto.getUserDto());
        }
        userMapper.toDto(userRepository.save(user));
        product.setProductName(productDto.getProductName());
        product.setQuantity(productDto.getQuantity());
        product.setUser(userMapper.toEntity(productDto.getUserDto()));
        productRepository.save(product);
        return product;
        }

    @Override
    public Product getProduct(Long productId) throws ProductNotFoundException{
        Optional<Product> op = productRepository.findById(productId);
        if(op.isPresent()) {
            return op.get();
        } else {
            throw new ProductNotFoundException("ProductIsNotFoundWithId"+productId);
        }
    }
}
