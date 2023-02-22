package com.example.service;


import com.example.entity.Services;
import com.example.entity.SubService;
import com.example.exceptions.subservice.SubServiceExistException;
import com.example.exceptions.subservice.SubServiceNotFoundException;
import com.example.repository.SubServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubServiceServiceImpl implements SubServiceService {

    private final SubServiceRepository subServiceRepository;
    private final ServicesServiceImpl service;



    @Override
    public SubService create(SubService subService) {
        try {
            Services services = service.findById(subService.getServices().getId());
            subService.setServices(services);
            return subServiceRepository.save(subService);
        } catch (DataIntegrityViolationException e) {
            throw new SubServiceExistException("a sub service already exists with this name in this service.");
        }
    }

    @Override
    public SubService findById(Long id) {
        return subServiceRepository.findById(id).orElseThrow(
                () -> new SubServiceNotFoundException("no sub service found with this ID.")
        );
    }

    @Override
    public List<SubService> findAllByServiceId(Long id) {
        return subServiceRepository.findAllByServices(id);
    }

    @Override
    public SubService update(SubService subService) {
        return subServiceRepository.save(subService);
    }
}
