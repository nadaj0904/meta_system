package com.example.nproject.mapper;

import com.example.nproject.dto.MetaStdWordDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MetaStdInsertMapper {
    int insertStdWord(MetaStdWordDto dto);

    int countByStdWordName(String stdWordName);
}
