package com.samyuktha.foodcatalogue.service;

import com.samyuktha.foodcatalogue.dto.FoodCataloguePage;
import com.samyuktha.foodcatalogue.dto.FoodItemDTO;
import com.samyuktha.foodcatalogue.dto.Restaurant;
import com.samyuktha.foodcatalogue.entity.FoodItem;
import com.samyuktha.foodcatalogue.mapper.FoodItemMapper;
import com.samyuktha.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {
    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;


    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItemSavedinDB =foodItemRepo.save(FoodItemMapper .INSTANCE.mapFromFoodItemDTOtoFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFromFoodItemtoFoodItemDTO(foodItemSavedinDB);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCataloguePage(foodItemList, restaurant);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemsList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId, Restaurant.class);
        //whatever response is coming from restaurant ms will be mapped to Restaurant class(dto)

    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
        //fetching food items from food item repo based on restaurant id, repo handles all database operations
    }
}
