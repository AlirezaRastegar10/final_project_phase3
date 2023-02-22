package com.example.controller;


import com.example.dto.customer.ChangeCustomerPasswordDto;
import com.example.dto.customer.CreateCustomerDto;
import com.example.dto.customer.GetCustomerDto;
import com.example.dto.customer.GetModifiedCustomerPasswordTimeDto;
import com.example.entity.Customer;
import com.example.mappers.CustomerMapperImpl;
import com.example.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerServiceImpl customerService;
    private final CustomerMapperImpl customerMapper;

    @PostMapping("/create")
    public GetCustomerDto create(@Valid @RequestBody CreateCustomerDto customerDto) {
        Customer customer = customerMapper.dtoToCustomer(customerDto);
        return customerMapper.customerToDto(customerService.create(customer));
    }

    @GetMapping("/findById/{id}")
    public GetCustomerDto findById(@PathVariable Long id) {
        return customerMapper.customerToDto(customerService.findById(id));
    }

    @GetMapping("/findAll")
    public List<GetCustomerDto> findAll() {
        return customerMapper.customerListToDtoList(customerService.findAll());
    }

    @PutMapping("/changePassword")
    public GetModifiedCustomerPasswordTimeDto changePassword(@Valid @RequestBody ChangeCustomerPasswordDto changeCustomerPasswordDto) {
        Customer customer = customerMapper.changePasswordDtoToCustomer(changeCustomerPasswordDto);
        return customerService.changePassword(customer);
    }

    @GetMapping("/search")
    public List<GetCustomerDto> findAll(@RequestParam(value = "search", required = false) String search) {
        return customerMapper.customerListToDtoList(customerService.search(search));
    }
}
