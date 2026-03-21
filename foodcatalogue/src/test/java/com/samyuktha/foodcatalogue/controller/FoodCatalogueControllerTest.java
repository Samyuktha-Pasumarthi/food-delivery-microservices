package com.samyuktha.foodcatalogue.controller;

import com.samyuktha.foodcatalogue.dto.FoodCataloguePage;
import com.samyuktha.foodcatalogue.dto.FoodItemDTO;
import com.samyuktha.foodcatalogue.service.FoodCatalogueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class FoodCatalogueControllerTest {
    @InjectMocks
    private FoodCatalogueController foodCatalogueController;

    @Mock
    private FoodCatalogueService foodCatalogueService;

    @Test
    public void testAddFoodItem() {
    FoodItemDTO mockFoodItems =
            new FoodItemDTO(1, "Food Item 1", "Description 1",true, 10, 1,1);


    when(foodCatalogueService.addFoodItem(mockFoodItems)).thenReturn(mockFoodItems);
    ResponseEntity<FoodItemDTO> response = foodCatalogueController.addFoodItem(mockFoodItems);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(mockFoodItems, response.getBody());

    verify(foodCatalogueService,times(1)).addFoodItem(mockFoodItems);
    }

    @Test
    public void fetchRestauDetailswithFoodMenu() {
        Integer mockRestaurantId = 1;
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();

        when(foodCatalogueService.fetchFoodCataloguePageDetails(mockRestaurantId)).thenReturn(foodCataloguePage);
        ResponseEntity<FoodCataloguePage> response = foodCatalogueController.fetchRestauDetailswithFoodMenu(mockRestaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foodCataloguePage, response.getBody());


    }

}
