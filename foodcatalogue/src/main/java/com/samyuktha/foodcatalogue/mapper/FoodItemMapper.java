package com.samyuktha.foodcatalogue.mapper;

import com.samyuktha.foodcatalogue.dto.FoodItemDTO;
import com.samyuktha.foodcatalogue.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {
    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);
    FoodItem mapFromFoodItemDTOtoFoodItem(FoodItemDTO foodItemDTO);
    FoodItemDTO mapFromFoodItemtoFoodItemDTO(FoodItem foodItem);


}
