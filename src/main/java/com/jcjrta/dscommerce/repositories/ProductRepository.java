package com.jcjrta.dscommerce.repositories;

import com.jcjrta.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {



}
