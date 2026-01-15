package com.Samyuktha.order.service;

import com.Samyuktha.order.dto.OrderDTO;
import com.Samyuktha.order.dto.OrderDTOFromFE;
import com.Samyuktha.order.dto.UserDTO;
import com.Samyuktha.order.entity.Order;
import com.Samyuktha.order.mapper.OrderMapper;
import com.Samyuktha.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    SequenceGenerator sequenceGenerator;
    @Autowired
    RestTemplate restTemplate;


    public OrderDTO saveOrderDetails(OrderDTOFromFE orderDetails) {
        Integer newOrderID = sequenceGenerator.generateNextOrderId();
        UserDTO userDTO = fetchUserDetailsFromUserId(orderDetails.getUserId());
        Order orderToBeSaved = new Order(newOrderID, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDTO );
        orderRepo.save(orderToBeSaved);
        return OrderMapper.INSTANCE.mapOrdertoOrderDTO(orderToBeSaved);
    }

    private UserDTO fetchUserDetailsFromUserId(Integer userId) {
        return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/"+userId, UserDTO.class);
    }
}
