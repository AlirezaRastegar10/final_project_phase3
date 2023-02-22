package com.example.service;


import com.example.dto.customer.GetModifiedCustomerPasswordTimeDto;
import com.example.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer create(Customer customer);
    Customer findById(Long id);
    List<Customer> findAll();
    Customer findByEmail(String email);
    GetModifiedCustomerPasswordTimeDto changePassword(Customer customer);

    List<Customer> search(String request);
}
