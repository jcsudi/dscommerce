package com.jcjrta.dscommerce.controllers;

import com.jcjrta.dscommerce.dto.ProductDTO;
import com.jcjrta.dscommerce.entities.Product;
import com.jcjrta.dscommerce.repositories.ProductRepository;
import com.jcjrta.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ProductDTO find(@PathVariable Long id){
        ProductDTO dt = service.f(id);
        return dt;
    }

    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<ProductDTO> r= service.findAll(pageable);
        return r;
        //return service.findAll();
    }
}
