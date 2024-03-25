package com.doanchung.springshop.repositories;

import com.doanchung.springshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepo extends JpaRepository<Product,Long> {
}
