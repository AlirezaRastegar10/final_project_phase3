package com.example.service;


import com.example.entity.Comments;
import com.example.entity.Customer;
import com.example.entity.Expert;
import com.example.entity.Orders;
import com.example.entity.enumeration.ExpertStatus;
import com.example.exceptions.user.UserInActiveException;
import com.example.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ExpertServiceImpl expertService;
    private final OrderServiceImpl orderService;
    private final CustomerServiceImpl customerService;

    @Override
    public Comments create(Comments comments) {
        Customer customer = customerService.findByEmail(comments.getEmail());
        Orders orders = orderService.findPaidOrder(comments.getOrders().getId(), customer.getId());
        comments.setExpert(orders.getOfferSelected().getExpert());
        expertService.updateExpertScore(comments.getScore(), orders.getOfferSelected().getExpert());
        return commentRepository.save(comments);
    }

    @Override
    public List<Comments> showExpertScore(Comments comments) {
        Expert expert = expertService.findByEmail(comments.getEmail());
        if (expert.getStatus() == ExpertStatus.INACTIVE) {
            throw new UserInActiveException("your account has been deactivated.");
        }
        return commentRepository.findExpertComment(expert.getId());
    }
}
