package com.doanchung.springshop.repositories;

import com.doanchung.springshop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    /**
     * - JpaRepository<Category, Long> => Long là kiểu của id
     */

}
