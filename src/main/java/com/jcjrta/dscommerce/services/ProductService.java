package com.jcjrta.dscommerce.services;

import com.jcjrta.dscommerce.dto.ProductDTO;
import com.jcjrta.dscommerce.entities.Product;
import com.jcjrta.dscommerce.repositories.ProductRepository;
import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO find(Long id){
        Optional<Product> result = repository.findById(id);
        Product p = result.get();
        ProductDTO productDTO = new ProductDTO(p);
        return productDTO;
    }
}
