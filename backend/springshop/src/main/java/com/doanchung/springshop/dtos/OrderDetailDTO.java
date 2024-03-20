package com.doanchung.springshop.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    /**
     * - id will be showed when data dc insert to db
     * - total price can 0 (>=)
     */
    @JsonProperty("order_id")
    @Min(value = 1, message = "order id must >= 1")
    private Long orderId;

    @JsonProperty("product_id")
    @Min(value = 0, message = "product id must > 0")
    private Long productId;

    @Min(value = 0, message = "price must be >= 0")
    private Long price;

    @Min(value=1, message = "number_of_products must be >= 1")
    @JsonProperty("number_of_products")
    private int numberOfProducts;

    //can be 0
    @JsonProperty("total_money")
    @Min(value = 0,message = "total money must >= 0")
    private Float totalMoney;

    private String color;


}
