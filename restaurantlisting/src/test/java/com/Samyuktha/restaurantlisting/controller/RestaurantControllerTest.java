package com.Samyuktha.restaurantlisting.controller;


import com.Samyuktha.restaurantlisting.dto.RestaurantDTO;
import com.Samyuktha.restaurantlisting.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    @InjectMocks
    RestaurantController restaurantController;

    @Mock
    RestaurantService restaurantService;

    @Test
    public void testFetchAllRestaurants() {
       //set up test data as service is not being called
       List<RestaurantDTO> mockRestaurants = Arrays.asList(
               new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
               new RestaurantDTO(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
       );
         //mock the service method to return the test data
         when(restaurantService.findAllRestaurants()).thenReturn(mockRestaurants);

         //call the controller method
        ResponseEntity<List<RestaurantDTO>> response =restaurantController.fetchAllRestaurants();

        //Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurants, response.getBody());

        //verify that the service method was called once
        verify(restaurantService, times(1)).findAllRestaurants();
    }

    @Test
    public void testSaveRestaurant() {
        //set up test data
        RestaurantDTO mockRestaurant = new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");

        //mock the service method to return the test data
        when(restaurantService.addRestaurantInDB(mockRestaurant)).thenReturn(mockRestaurant);

        //call the controller method
        ResponseEntity<RestaurantDTO> response = restaurantController.saveRestaurant(mockRestaurant);

        //Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());

        //verify that the service method was called once with the correct argument
        verify(restaurantService, times(1)).addRestaurantInDB(mockRestaurant);
    }

    @Test
    public void testFindRestaurantById() {
        //set up test data
        Integer mockRestaurantId = 1;
        RestaurantDTO mockRestaurant = new RestaurantDTO(mockRestaurantId, "Restaurant 1", "Address 1", "city 1", "Desc 1");

        //mock the service method to return the test data
        when(restaurantService.fetchRestaurantById(mockRestaurantId)).thenReturn(new ResponseEntity<>(mockRestaurant, HttpStatus.OK));

        //call the controller method
        ResponseEntity<RestaurantDTO> response = restaurantController.findRestaurantById(mockRestaurantId);

        //Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());

        //verify that the service method was called once with the correct argument
        verify(restaurantService, times(1)).fetchRestaurantById(mockRestaurantId);
    }
}
