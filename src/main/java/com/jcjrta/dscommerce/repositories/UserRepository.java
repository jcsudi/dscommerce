package com.jcjrta.dscommerce.repositories;

import com.jcjrta.dscommerce.entities.Product;
import com.jcjrta.dscommerce.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail (String email);
}
