package com.doanchung.springshop.controllers;

import com.doanchung.springshop.dtos.OrderDTO;
import com.doanchung.springshop.dtos.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {

    @PostMapping("")
    public ResponseEntity<?> createOrder(
            @Valid
            @RequestBody OrderDTO orderDTO,
            BindingResult bindingResult
    ) {
        try {
            //handle error message
            ArrayList<String> errorMessages = new ArrayList<>();
            if (bindingResult.hasErrors()) {
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errorMessages.add(error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(errorMessages);
            }

            return ResponseEntity.ok("Create order oki");
        } catch (Exception e) {
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity getOrdersById(
            @Valid
            @PathVariable("user_id") Long userId
    ) {
        try {
            return ResponseEntity.ok("Done Lấy ra danh sách order từ user_id");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    //công việc của admin
    //PUT http://localhost:8088/api/v1/orders/2
    public ResponseEntity<?> updateOrderById(
            @Valid @PathVariable Long id,
            @Valid @RequestBody OrderDTO orderDTO
    ) {
        return ResponseEntity.ok("Update ỏdẻ by id oke.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder (
        @Valid @PathVariable Long id
    ){
        //xóa mềm, update trường active/status, ko dùng sql
        return ResponseEntity.ok("Order delete oki.");

    }


}
