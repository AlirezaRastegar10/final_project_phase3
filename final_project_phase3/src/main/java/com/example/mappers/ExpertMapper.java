package com.example.mappers;


import com.example.dto.expert.*;
import com.example.entity.Expert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpertMapper {

    GetExpertDto expertToDto(Expert expert);
    @Mapping(target = "imageUrl", ignore = true)
    Expert expertDtoToExpert(CreateExpertDto createExpertDto);
    List<GetExpertDto> expertListToDtoList(List<Expert> expertList);

    Expert changePasswordDtoToExpert(ChangeExpertPasswordDto changeExpertPasswordDto);
    GetExpertSubServiceDto expertToGetSubServiceDto(Expert expert);
    Expert expertSubServiceToExpert(ExpertToSubServiceDto expertToSubServiceDto);
    Expert saveImageDtoToExpert(SaveImageDto saveImageDto);
}
