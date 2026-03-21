package com.Samyuktha.order.controller;

import com.Samyuktha.order.dto.*;
import com.Samyuktha.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    @Test
    public void testSaveOrder() {
        List<FoodItemsDTO> mockFoodItems = Arrays.asList(
                new FoodItemsDTO(1, "Food Item 1", "Description 1",true, 10, 1,1),

                new FoodItemsDTO(2, "Food Item 2", "Description 2",true, 10, 1,1));

        Restaurant mockRestaurant = new Restaurant(1, "Restaurant 1", "Address 1", "City 1", "Desc 1");
        Integer mockUserId = 1;
        OrderDTOFromFE mockOrderDetails = new OrderDTOFromFE(mockFoodItems, mockRestaurant, mockUserId);

        OrderDTO mockOrderDTO = new OrderDTO();

        when(orderService.saveOrderDetails(mockOrderDetails)).thenReturn(mockOrderDTO);

        ResponseEntity<OrderDTO> response = orderController.saveOrder(mockOrderDetails);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockOrderDTO, response.getBody());

        verify(orderService, times(1)).saveOrderDetails(mockOrderDetails);

    }
}
