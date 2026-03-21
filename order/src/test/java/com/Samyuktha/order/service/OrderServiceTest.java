package com.Samyuktha.order.service;


import com.Samyuktha.order.dto.*;
import com.Samyuktha.order.entity.Order;
import com.Samyuktha.order.mapper.OrderMapper;
import com.Samyuktha.order.repo.OrderRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepo orderRepo;

    @Mock
    RestTemplate restTemplate;

    @Mock
    SequenceGenerator sequenceGenerator;


    @Test
    public void testSaveOrderDetails() {
        List<FoodItemsDTO> mockFoodItems = Arrays.asList(
                new FoodItemsDTO(1, "Food Item 1", "Description 1",true, 10, 1,1),

                new FoodItemsDTO(2, "Food Item 2", "Description 2",true, 10, 1,1));

        Restaurant mockRestaurant = new Restaurant(1, "Restaurant 1", "Address 1", "City 1", "Desc 1");
        Integer mockUserId = 1;
        OrderDTOFromFE mockOrderDetails = new OrderDTOFromFE(mockFoodItems, mockRestaurant, mockUserId);

        Integer mockOrderId = 1;
        UserDTO mockUserDTO = new UserDTO();

        Order mockOrderToBeSaved = new Order(mockOrderId, mockOrderDetails.getFoodItemsList(), mockOrderDetails.getRestaurant(), mockUserDTO );
        OrderDTO mockOrderDTO = OrderMapper.INSTANCE.mapOrdertoOrderDTO(mockOrderToBeSaved);

        when(sequenceGenerator.generateNextOrderId()).thenReturn(mockOrderId);
        when(restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/"+mockUserId, UserDTO.class)).thenReturn(mockUserDTO);
        when(orderRepo.save(mockOrderToBeSaved)).thenReturn(mockOrderToBeSaved);

        OrderDTO result = orderService.saveOrderDetails(mockOrderDetails);

        assertEquals(mockOrderDTO, result);

        verify(orderRepo,times(1)).save(mockOrderToBeSaved);
        assertDoesNotThrow(() -> orderService.saveOrderDetails(mockOrderDetails));



    }


}
