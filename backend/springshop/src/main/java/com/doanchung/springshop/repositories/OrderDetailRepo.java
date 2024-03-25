package com.doanchung.springshop.repositories;

import com.doanchung.springshop.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findOrderDetailById(Long orderID);
    
}
