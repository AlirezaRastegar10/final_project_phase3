package com.example.controller;


import com.example.dto.offer.CreateOfferDto;
import com.example.dto.offer.GetOfferDto;
import com.example.dto.offer.GetOfferForCustomerDto;
import com.example.dto.offer.SelectOfferDto;
import com.example.dto.order.GetOrderByCustomerEmailDto;
import com.example.dto.order.GetOrderWithOfferDto;
import com.example.entity.Offers;
import com.example.mappers.OfferMapperImpl;
import com.example.mappers.OrderMapperImpl;
import com.example.service.OfferServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/offer")
public class OfferController {

    private final OfferServiceImpl offerService;
    private final OfferMapperImpl offerMapper;
    private final OrderMapperImpl orderMapper;

    @PostMapping("/create")
    public GetOfferDto create(@Valid @RequestBody CreateOfferDto createOfferDto) {
        Offers offers = offerMapper.offerDtoToOffer(createOfferDto);
        return offerMapper.offerToDto(offerService.create(offers));
    }

    @PostMapping("/findAll/orderByProposedPrice")
    public List<GetOfferForCustomerDto> orderByProposedPrice(@Valid @RequestBody GetOrderByCustomerEmailDto getOrderByCustomerEmailDto) {
        Offers offers = offerMapper.offerCustomerDtoToOffer(getOrderByCustomerEmailDto);
        return offerMapper.offerListToDtoList(offerService.orderByProposedPrice(offers));
    }

    @PostMapping("/findAll/orderByScore")
    public List<GetOfferForCustomerDto> orderByScore(@Valid @RequestBody GetOrderByCustomerEmailDto getOrderByCustomerEmailDto) {
        Offers offers = offerMapper.offerCustomerDtoToOffer(getOrderByCustomerEmailDto);
        return offerMapper.offerListToDtoList(offerService.orderByScore(offers));
    }

    @PostMapping("/selectOffer")
    public GetOrderWithOfferDto selectOffer(@Valid @RequestBody SelectOfferDto selectOfferDto) {
        Offers offers = offerMapper.selectOfferDtoToOffer(selectOfferDto);
        return orderMapper.orderToOfferDto(offerService.selectOffer(offers));
    }
}
