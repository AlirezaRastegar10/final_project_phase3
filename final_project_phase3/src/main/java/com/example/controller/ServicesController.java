package com.example.controller;


import com.example.dto.service.GetServicesDto;
import com.example.dto.service.ServicesDto;
import com.example.entity.Services;
import com.example.mappers.ServicesMapperImpl;
import com.example.service.ServicesServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/services")
public class ServicesController {

    private final ServicesServiceImpl service;
    private final ServicesMapperImpl servicesMapper;

    @PostMapping("/create")
    public GetServicesDto create(@Valid @RequestBody ServicesDto servicesDto) {
        Services services = servicesMapper.dtoToServices(servicesDto);
        return servicesMapper.servicesToDto(service.save(services));
    }

    @GetMapping("/findAll")
    public List<GetServicesDto> findAll() {
        return servicesMapper.servicesListToDtoList(service.findAll());
    }

    @GetMapping("/findById/{id}")
    public GetServicesDto findById(@PathVariable Long id) {
        return servicesMapper.servicesToDto(service.findById(id));
    }
}
