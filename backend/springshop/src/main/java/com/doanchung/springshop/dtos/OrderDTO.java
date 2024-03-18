package com.doanchung.springshop.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    /**
     * - phone number can dup cause user B can use phone number of user A to order.
     * - order_date will be auto created.
     * - status : auto created - pending
     * - shipping_date: auto created after calculate
     */

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("fullname")
    private String fullName;

    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

    private String note;

    @JsonProperty("total_money")
    private Float totalMoney;

    private Float shippingMethod;

    //5p53

}
