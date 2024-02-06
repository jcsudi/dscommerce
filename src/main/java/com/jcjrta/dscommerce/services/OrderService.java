package com.jcjrta.dscommerce.services;

import com.jcjrta.dscommerce.dto.OrderDTO;
import com.jcjrta.dscommerce.dto.ProductDTO;
import com.jcjrta.dscommerce.entities.Order;
import com.jcjrta.dscommerce.repositories.OrderRepository;
import com.jcjrta.dscommerce.services.exceptions.ResourceNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order dto = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundExceptions("Recurso não encontrado"));
                return new OrderDTO(dto);
    }
}
