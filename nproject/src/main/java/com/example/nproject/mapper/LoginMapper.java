package com.example.nproject.mapper;

import com.example.nproject.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
    UserDto login(@Param("userId") String userId, @Param("password") String password);
}
