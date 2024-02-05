package com.jcjrta.dscommerce.controllers;

import com.jcjrta.dscommerce.dto.ProductDTO;
import com.jcjrta.dscommerce.dto.ProductMinDTO;
import com.jcjrta.dscommerce.entities.Product;
import com.jcjrta.dscommerce.repositories.ProductRepository;
import com.jcjrta.dscommerce.services.ProductService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity< Page<ProductMinDTO>> findAll(
            @RequestParam(name = "name", defaultValue = "") String name, Pageable pageable){
        Page<ProductMinDTO> r= service.findAll(name, pageable);
        return ResponseEntity.ok(r);
        //return service.findAll();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto){
       ProductDTO dt = service.insert(dto);
       URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dt.getId()).toUri();
       return ResponseEntity.created(uri).body(dt);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto){
        ProductDTO dt = service.update(id, dto);
        return ResponseEntity.ok(dt);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
