package com.doanchung.springshop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    //show all category
    @GetMapping("")
    //http://localhost:8088/api/v1/categories
    public ResponseEntity<String> getAllCategory() {
        return ResponseEntity.ok("hihi");
    }
    @PostMapping("")
    public ResponseEntity<String> insertCategory() {
        return ResponseEntity.ok("insert cate");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id) {
        return ResponseEntity.ok("put cate with id = " + id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok("delete cate with id = "+id);
    }
}
