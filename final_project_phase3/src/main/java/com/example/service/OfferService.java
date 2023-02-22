package com.example.service;


import com.example.entity.Offers;
import com.example.entity.Orders;

import java.util.List;

public interface OfferService {

    Offers create(Offers offers);
    List<Offers> orderByProposedPrice(Offers offers);
    List<Offers> orderByScore(Offers offers);
    Orders selectOffer(Offers offers);
}
