package com.example.mappers;


import com.example.dto.order.*;
import com.example.dto.payment.CreatePaymentDto;
import com.example.entity.Orders;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    GetOrderDto orderToDto(Orders orders);
    Orders orderDtoToOrder(CreateOrderDto createOrderDto);
    List<GetOrderForExpertDto> orderListToDtoList(List<Orders> ordersList);
    GetOrderWithOfferDto orderToOfferDto(Orders orders);
    List<GetOrderWithOfferDto> orderListToOrderListDto(List<Orders> ordersList);
    Orders orderEmailDtoToOrder(GetOrderByEmailDto getOrderByEmailDto);
    Orders orderCustomerEmailDtoToOrder(GetOrderByCustomerEmailDto getOrderByCustomerEmailDto);
    Orders paymentDtoToOrder(CreatePaymentDto createPaymentDto);
}
