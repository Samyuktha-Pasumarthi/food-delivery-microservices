package com.Samyuktha.order.mapper;

import com.Samyuktha.order.dto.OrderDTO;
import com.Samyuktha.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order mapOrderDTOtoOrder(OrderDTO orderDTO);
    OrderDTO mapOrdertoOrderDTO(Order order);


}
