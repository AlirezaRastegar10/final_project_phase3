package com.example.service;


import com.example.entity.Customer;
import com.example.entity.Expert;
import com.example.entity.Offers;
import com.example.entity.Orders;
import com.example.entity.enumeration.ExpertStatus;
import com.example.exceptions.offer.OfferNotFoundException;
import com.example.exceptions.order.LessProposedPriceException;
import com.example.exceptions.time.LessTimeException;
import com.example.exceptions.user.UserInActiveException;
import com.example.repository.OffersRepository;
import com.example.utils.LocalDateTimeValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OffersRepository offersRepository;
    private final ExpertServiceImpl expertService;
    private final OrderServiceImpl orderService;
    private final LocalDateTimeValidation dateTimeValidation;
    private final CustomerServiceImpl customerService;

    @Override
    public Offers create(Offers offers) {
        Expert expert = expertService.findAcceptedExpertByEmail(offers.getEmail());
        if (expert.getStatus() == ExpertStatus.INACTIVE) {
            throw new UserInActiveException("your account has been deactivated.");
        }
        Orders order = orderService.findByIdAndStatus(offers.getOrder().getId(), expert.getSubServiceSet());
        if (offers.getProposedPrice() < order.getSubService().getBasePrice()) {
            throw new LessProposedPriceException("your bid price is lower than the sub service base price.");
        }
        LocalDateTime suggestedTime = dateTimeValidation.validator(offers.getSuggestedDate());
        if (suggestedTime.isBefore(order.getDateAndTime())) {
            throw new LessTimeException("the suggested time to start the work is less than the time entered by the customer.");
        }
        LocalDateTime durationOfWork = dateTimeValidation.validator(offers.getWorkTime());
        if (durationOfWork.isBefore(suggestedTime) || durationOfWork.isBefore(order.getDateAndTime())) {
            throw new LessTimeException("the time entered to do the work should not be less than your suggested time and the customer's suggested time.");
        }
        offers.setExpert(expert);
        offers.setSuggestedTime(suggestedTime);
        offers.setDurationOfWork(durationOfWork);
        offers.setOrder(order);

        orderService.updateOrderStatusToSelect(order, offers);

        return offersRepository.save(offers);
    }

    @Override
    public List<Offers> orderByProposedPrice(Offers offers) {
        Customer customer = customerService.findByEmail(offers.getEmail());
        Orders orders = orderService.findByIdAndCustomerId(offers.getOrder().getId(), customer.getId());
        return offersRepository.findByOrderIdOrderByProposedPrice(orders.getId());
    }

    @Override
    public List<Offers> orderByScore(Offers offers) {
        Customer customer = customerService.findByEmail(offers.getEmail());
        Orders orders = orderService.findByIdAndCustomerId(offers.getOrder().getId(), customer.getId());
        return offersRepository.findByOrderIdOrderByExpertScore(orders.getId());
    }

    @Override
    public Orders selectOffer(Offers offers) {
        Customer customer = customerService.findByEmail(offers.getEmail());
        Orders orders = orderService.findByIdAndCustomerId(offers.getOrder().getId(), customer.getId());
        Offers newOffer = offersRepository.findByIdAndOrderId(offers.getId(), orders.getId()).orElseThrow(
                () -> new OfferNotFoundException("no offers with this ID were found for this order.")
        );

        return orderService.updateOrderStatusToComePlace(orders, newOffer);
    }
}
