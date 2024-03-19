package com.doanchung.springshop.controllers;

import com.doanchung.springshop.dtos.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    /**
     * 1 => http://localhost:8088/api/v1/products
     * 2 => http://localhost:8088/api/v1/products?page=1&limit=12
     * 3 => http://localhost:8088/api/v1/products/6
     * 4 => http://localhost:8088/api/v1/products/6
     * <p>
     * - MULTIPART_FORM_DATA_VALUE
     * - Dùng thư viện UUID nối vào trước tên cũ để tạo ra 1 cái tên độc nhất
     * <p>
     * <p>
     * - JSON raw
     * {
     * "name" : "ipad 2023",
     * "price" : "812.34",
     * "thumbnail" : "",
     * "description" : "This is tested product",
     * "category_id" : 1
     * }
     * <p>
     * - form data
     * "name" : "ipad 2023",
     * "price" : "812.34",
     * "description" : "This is tested product",
     * "category_id" : 1
     * "files" : nhiều file
     */

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProduct(
            @Valid
            @ModelAttribute ProductDTO productDTO,
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
            List<MultipartFile> files = productDTO.getFiles();
            files = files == null ? new ArrayList<MultipartFile>() : files;
            for (MultipartFile file : files) {
                System.out.println(productDTO.getCategoryId());
                //thumbnail không bắt buộc phải thêm ảnh nên nếu mà ảnh null thì vẫn cho người dùng up ảnh lên
                if (file != null) {
                    if (file.getSize() == 0) {
                        continue;
                    }
                    if (file.getSize() > 10 * 1024 * 1024) {
                        //size > 10MB
                        //throw new ResponseStatusException( HttpStatus.PAYLOAD_TOO_LARGE, "File is too large, maximum size is 10MB.");
                        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is too large, maximum size is 10MB.");
                    }
                    String checkContentType = file.getContentType();
                    System.out.println(checkContentType);
                    if (checkContentType == null || !checkContentType.startsWith("image/")) {
                        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File must be an image.");
                    }
                    //save file + cập nhật DTP
                    String fileName = storeFile(file);
                    //save vao` db
//                productDTO.set
                }
            }
            return ResponseEntity.ok(String.format("create product done"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    private String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //Tên mới phải là duy nhất nhé
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        //Đường dẫn thư mục muốn lưu file | dùng java.nio
        Path uploadDir = Paths.get("uploads");
        //Kiểm tra và tạo thư mục nếu nó không tồn tại
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        //Đường dẫn đến file
        Path myDestination = Paths.get(uploadDir.toString(), uniqueFileName);
        //Sao chép file vào thư mục đích
        Files.copy(file.getInputStream(), myDestination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }

    @GetMapping("")
    public ResponseEntity<String> getProducts(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok("get products here");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(
            @PathVariable("id") String product_id
    ) {
        return ResponseEntity.ok(String.format("Product with id: " + product_id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable("id") Integer product_Id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body("Product " + product_Id + " deleted successfully");
    }

}
