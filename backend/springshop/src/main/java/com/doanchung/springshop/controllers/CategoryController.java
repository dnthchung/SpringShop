package com.doanchung.springshop.controllers;

import com.doanchung.springshop.dtos.CategoryDTO;
import com.doanchung.springshop.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@Validated => validate ở level class thay vì nhảy vào bên trong hàm
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<?> createCategory(
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
        //#20
        categoryService.createCategory(categoryDTO);

        return ResponseEntity.ok("insert cate " + categoryDTO);
    }


    //show all category
    @GetMapping("")
    public ResponseEntity<String> getAllCategory(
            //http://localhost:8088/api/v1/categories
            //http://localhost:8088/api/v1/categories?page=1&limit=10
            //page -> tham số gửi qua trình duyệt
            //tham số hàm trong jav thoi
            //đôi lúc sẽ khác nhau
            //@RequestParam("number_of_product") int numberOfProduct
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok("hihi" + String.format("getAllCate, page = %d, limit = %d", page, limit));
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
