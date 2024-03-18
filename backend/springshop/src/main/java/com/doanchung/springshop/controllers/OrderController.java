package com.doanchung.springshop.controllers;

import com.doanchung.springshop.dtos.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @PostMapping("")
    public ResponseEntity<?> createOrder(
            @Valid
            @RequestBody UserDTO userDTO,
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
            //check password == retype password
            if (!userDTO.getPassword().equals(userDTO.getRetypePassword())){
                return ResponseEntity.badRequest().body("Password does not match");
            }
            return ResponseEntity.ok("Register oki");
        } catch (Exception e) {
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
