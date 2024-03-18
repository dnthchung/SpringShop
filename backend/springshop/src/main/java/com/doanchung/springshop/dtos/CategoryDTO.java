package com.doanchung.springshop.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

//@Data = toString()
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
//    @NotNull(message = "Id must not be null")
//    private Integer id; // Use Integer instead of int

    @NotEmpty(message = "Name must not be empty")
    private String name;

}
