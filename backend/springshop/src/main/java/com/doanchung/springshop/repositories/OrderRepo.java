package com.doanchung.springshop.repositories;

import com.doanchung.springshop.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByUserId (Long userId);
}
