package com.jcjrta.dscommerce.repositories;

import com.jcjrta.dscommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
