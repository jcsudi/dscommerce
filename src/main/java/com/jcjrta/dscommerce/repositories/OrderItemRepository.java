package com.jcjrta.dscommerce.repositories;

import com.jcjrta.dscommerce.entities.Order;
import com.jcjrta.dscommerce.entities.OrderItem;
import com.jcjrta.dscommerce.entities.OrderItemPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk> {
}
