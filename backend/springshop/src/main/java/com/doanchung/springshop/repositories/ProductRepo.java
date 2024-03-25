package com.doanchung.springshop.repositories;

import com.doanchung.springshop.models.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {

    Product findProductByName(String productName);

    boolean existsByName(String productName);
    Page<Product> findAll(Pageable pageable);//phan trang
}
