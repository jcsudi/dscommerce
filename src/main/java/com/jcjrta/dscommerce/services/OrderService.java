package com.jcjrta.dscommerce.services;

import com.jcjrta.dscommerce.dto.OrderDTO;
import com.jcjrta.dscommerce.dto.OrderItemDTO;
import com.jcjrta.dscommerce.dto.ProductDTO;
import com.jcjrta.dscommerce.entities.*;
import com.jcjrta.dscommerce.repositories.OrderItemRepository;
import com.jcjrta.dscommerce.repositories.OrderRepository;
import com.jcjrta.dscommerce.repositories.ProductRepository;
import com.jcjrta.dscommerce.services.exceptions.ResourceNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order dto = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundExceptions("Recurso não encontrado"));
                return new OrderDTO(dto);
    }
    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order();
        order.setMomemt(Instant.now());
        order.setStaus(OrderStatus.WAITING_PAYMENT);
        User user= userService.authenticated();
        order.setClient(user);

        for (OrderItemDTO itemDTO : dto.getItems()){
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }
        repository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }
}
