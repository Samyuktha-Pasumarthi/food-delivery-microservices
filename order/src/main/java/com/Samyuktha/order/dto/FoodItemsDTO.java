package com.Samyuktha.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemsDTO {
    private Integer id;
    private String itemName;
    private String itemDescription;
    @JsonProperty("veg")
    private boolean isVeg;
    private Integer price;
    private Integer restaurantId;
    private Integer quantity;
}
