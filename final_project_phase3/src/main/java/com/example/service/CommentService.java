package com.example.service;

import com.example.entity.Comments;

import java.util.List;

public interface CommentService {

    Comments create(Comments comments);
    List<Comments> showExpertScore(Comments comments);
}
