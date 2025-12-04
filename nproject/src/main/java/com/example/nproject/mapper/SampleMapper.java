package com.example.nproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.nproject.dto.SampleDto;

@Mapper
public interface SampleMapper {
    SampleDto selectVersion();
}
