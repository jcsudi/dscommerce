package com.jcjrta.dscommerce.controllers;

import com.jcjrta.dscommerce.dto.ProductDTO;
import com.jcjrta.dscommerce.entities.Product;
import com.jcjrta.dscommerce.repositories.ProductRepository;
import com.jcjrta.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ProductDTO find(@PathVariable Long id){
        ProductDTO dt = service.find(id);
        return dt;
    }
}
