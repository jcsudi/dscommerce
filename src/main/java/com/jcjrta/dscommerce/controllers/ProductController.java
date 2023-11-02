package com.jcjrta.dscommerce.controllers;

import com.jcjrta.dscommerce.dto.ProductDTO;
import com.jcjrta.dscommerce.entities.Product;
import com.jcjrta.dscommerce.repositories.ProductRepository;
import com.jcjrta.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> find(@PathVariable Long id){
        ProductDTO dt = service.f(id);
        return ResponseEntity.ok(dt);
    }

    @GetMapping
    public ResponseEntity< Page<ProductDTO>> findAll(Pageable pageable){
        Page<ProductDTO> r= service.findAll(pageable);
        return ResponseEntity.ok(r);
        //return service.findAll();
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto){
       ProductDTO dt = service.insert(dto);
       URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dt.getId()).toUri();
       return ResponseEntity.created(uri).body(dt);
    }
}
