package com.Samyuktha.order.controller;

import com.Samyuktha.order.dto.OrderDTO;
import com.Samyuktha.order.dto.OrderDTOFromFE;
import com.Samyuktha.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTOFromFE orderDetails) {
        OrderDTO orderSaveDInDB = orderService.saveOrderDetails(orderDetails);
        return new ResponseEntity<>(orderSaveDInDB, HttpStatus.CREATED);
    }
}
