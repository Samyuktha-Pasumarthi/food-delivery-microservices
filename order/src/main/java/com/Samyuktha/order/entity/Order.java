package com.Samyuktha.order.entity;

import com.Samyuktha.order.dto.FoodItemsDTO;
import com.Samyuktha.order.dto.Restaurant;
import com.Samyuktha.order.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {

    private Integer orderId;

    private List<FoodItemsDTO> foodItemsList;
    private Restaurant restaurant;
    private UserDTO userDTO;

}
