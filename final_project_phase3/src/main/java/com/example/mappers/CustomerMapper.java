package com.example.mappers;


import com.example.dto.customer.ChangeCustomerPasswordDto;
import com.example.dto.customer.CreateCustomerDto;
import com.example.dto.customer.GetCustomerDto;
import com.example.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    GetCustomerDto customerToDto(Customer customer);
    Customer dtoToCustomer(CreateCustomerDto customerDto);
    List<GetCustomerDto> customerListToDtoList(List<Customer> customerList);
    Customer changePasswordDtoToCustomer(ChangeCustomerPasswordDto changeCustomerPasswordDto);
}
