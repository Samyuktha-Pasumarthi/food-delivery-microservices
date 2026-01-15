package com.Samyuktha.restaurantlisting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestaurantDTO {

    private Integer id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;
}
