package com.jcjrta.dscommerce.repositories;

import com.jcjrta.dscommerce.entities.Category;
import com.jcjrta.dscommerce.entities.OrderItem;
import com.jcjrta.dscommerce.entities.OrderItemPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
