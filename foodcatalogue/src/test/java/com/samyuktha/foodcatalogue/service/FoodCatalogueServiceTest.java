package com.samyuktha.foodcatalogue.service;

import com.samyuktha.foodcatalogue.dto.FoodCataloguePage;
import com.samyuktha.foodcatalogue.dto.FoodItemDTO;
import com.samyuktha.foodcatalogue.dto.Restaurant;
import com.samyuktha.foodcatalogue.entity.FoodItem;
import com.samyuktha.foodcatalogue.mapper.FoodItemMapper;
import com.samyuktha.foodcatalogue.repo.FoodItemRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.Mapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class FoodCatalogueServiceTest {
    @InjectMocks
    private FoodCatalogueService foodCatalogueService;

    @Mock
    private FoodItemRepo fooditemRepo;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    FoodItemMapper foodItemMapper;

    @Test
    public void testAddFoodItem() {
        FoodItemDTO foodItemDTO = new FoodItemDTO(1, "Food Item 1", "Description 1",true, 10, 1,1);
        FoodItem foodItem = FoodItemMapper.INSTANCE.mapFromFoodItemDTOtoFoodItem(foodItemDTO);

        when(fooditemRepo.save(foodItem)).thenReturn(foodItem);

        FoodItemDTO result = foodCatalogueService.addFoodItem(foodItemDTO);

        assertEquals(foodItemDTO, result);
        verify(fooditemRepo, times(1)).save(foodItem);
    }

    @Test
    public void testFetchFoodCataloguePageDetails() {
        int mockRestaurantId = 1;
        List<FoodItem> foodItemList = Arrays.asList(
                new FoodItem(1, "Food Item 1", "Description 1",true, 10, 1,1),
                new FoodItem(2, "Food Item 2", "Description 2",false, 15, 1,1)
                );
        Restaurant mockRestaurant = new Restaurant(1, "Restaurant 1", "Address 1", "City 1", "Desc 1");

        when(fooditemRepo.findByRestaurantId(mockRestaurantId)).thenReturn(foodItemList);

        when(restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+mockRestaurantId, Restaurant.class)).thenReturn(mockRestaurant);


        FoodCataloguePage result =  foodCatalogueService.fetchFoodCataloguePageDetails(mockRestaurantId);

        assertEquals(foodItemList, result.getFoodItemsList());
        assertEquals(mockRestaurant, result.getRestaurant());

        verify(fooditemRepo, times(1)).findByRestaurantId(mockRestaurantId);
        verify(restTemplate, times(1)).getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+mockRestaurantId, Restaurant.class);

    }










}
