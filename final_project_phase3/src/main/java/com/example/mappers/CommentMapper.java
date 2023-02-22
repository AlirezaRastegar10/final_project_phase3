package com.example.mappers;


import com.example.dto.comment.CommentForExpertDto;
import com.example.dto.comment.CreateCommentDto;
import com.example.dto.comment.GetCommentDto;
import com.example.dto.comment.GetCommentForExpertDto;
import com.example.entity.Comments;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comments commentDtoToComment(CreateCommentDto createCommentDto);
    GetCommentDto commentToDto(Comments comments);
    Comments commentForExpertToComment(CommentForExpertDto commentForExpertDto);
    List<GetCommentForExpertDto> commentToExpertScoreDto(List<Comments> comments);
}
