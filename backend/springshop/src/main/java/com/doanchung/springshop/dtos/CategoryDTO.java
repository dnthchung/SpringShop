package com.doanchung.springshop.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

//@Data = toString()
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @NotEmpty(message = "Lỗi hiện ra khi : Category name empty.")
    private String name;

}
