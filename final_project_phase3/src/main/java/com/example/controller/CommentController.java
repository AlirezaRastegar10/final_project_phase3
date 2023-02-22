package com.example.controller;


import com.example.dto.comment.CommentForExpertDto;
import com.example.dto.comment.CreateCommentDto;
import com.example.dto.comment.GetCommentDto;
import com.example.dto.comment.GetCommentForExpertDto;
import com.example.entity.Comments;
import com.example.mappers.CommentMapperImpl;
import com.example.service.CommentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
public class CommentController {

    private final CommentServiceImpl commentService;
    private final CommentMapperImpl commentMapper;

    @PostMapping("/create")
    public GetCommentDto create(@Valid @RequestBody CreateCommentDto createCommentDto) {
        Comments comments = commentMapper.commentDtoToComment(createCommentDto);
        return commentMapper.commentToDto(commentService.create(comments));
    }

    @PostMapping("/showMyScore")
    public List<GetCommentForExpertDto> showScore(@Valid @RequestBody CommentForExpertDto commentForExpertDto) {
        Comments comments = commentMapper.commentForExpertToComment(commentForExpertDto);
        return commentMapper.commentToExpertScoreDto(commentService.showExpertScore(comments));
    }
}
