package com.doanchung.springshop.controllers;

import com.doanchung.springshop.dtos.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {
    /**
     * - createOrderDetail
     * - getOrderDetail
     * - getOrderDetails
     * - updateOrderDetail
     * - deleteOrderDetail : return ok() or noContent() đều được
     */

    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(
            @Valid
            @RequestBody OrderDetailDTO newOrderDetail,
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
            return ResponseEntity.ok("Create order detail oki");
        } catch (Exception e) {
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(
            @Valid
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok("getOrderDetail with id = " + id);
    }

    //lấy ra danh sách các order_details của 1 order nào đó
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(
            @Valid
            @PathVariable("orderId") Long orderId
    ) {
        return ResponseEntity.ok("get Order Details with orderId = " + orderId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(
            @Valid
            @PathVariable("id") Long id,
            @RequestBody OrderDetailDTO newOrderDetailData,
            BindingResult bindingResult
    ) {
        try {
            ArrayList<String> errorMessages = new ArrayList<>();
            if (bindingResult.hasErrors()) {
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errorMessages.add(error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Update Order Detail with id = " + id
                    + ", new Order Detail Data: " + newOrderDetailData);
        } catch (Exception e) {
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetail(
            @Valid
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.noContent().build();
    }

}
