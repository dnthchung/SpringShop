package com.doanchung.springshop.controllers;

import com.doanchung.springshop.dtos.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@Validated => validate ở level class thay vì nhảy vào bên trong hàm
@RequestMapping("api/v1/categories")
public class CategoryController {


    //show all category
    //http://localhost:8088/api/v1/categories
    //http://localhost:8088/api/v1/categories?page=1&limit=10
    @GetMapping("")
    public ResponseEntity<String> getAllCategory(
            //page -> tham số gửi qua trình duyệt
            //tham số hàm trong jav thoi
            //đôi lúc sẽ khác nhau
            //@RequestParam("number_of_product") int numberOfProduct
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok("hihi" + String.format("getAllCate, page = %d, limit = %d", page, limit));
    }

    @PostMapping("")
    public ResponseEntity<?> insertCategory(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult bindingResult
    ) {
        ArrayList<String> errorMessages = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            // If there are validation errors, collect error messages
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }

            // Return error messages along with the response
            return ResponseEntity.badRequest().body(errorMessages);
        }

        // If there are no validation errors, proceed with inserting the category
        // Your logic for inserting category goes here

        return ResponseEntity.ok("insert cate " + categoryDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id) {
        return ResponseEntity.ok("put cate with id = " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok("delete cate with id = " + id);
    }
}
