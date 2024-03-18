package com.doanchung.springshop.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String name;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Max(value = 10000000, message = "Price must be less than or equal to 10,000,000")
    private Float price;

    private String thumbnail;
    private String description;

//    @NotNull(message = "Category ID is required")
    @JsonProperty("category_id")
    private Long categoryId;

    private List<MultipartFile> files;




    /**
     * Trong db :   category_id
     * Trong code:  categoryId
     *  => sử dụng JsonProperty để map chúng với nhau
     * ------------------------------------------------
     * created_at & updated_at tự sinh, kh cần tạo
     * ------------------------------------------------
     * @Min(value = 0, message = "Discount must be greater than or equal to 6")
     * @Max(value = 100, message = "Discount must be less than or equal to 166")
     * private int discount;
     * ------------------------------------------------
     * @NotNull(message = "Color ID is required")
     * @JsonProperty("color_id")
     * private Long colorId;
     *
     */
}
