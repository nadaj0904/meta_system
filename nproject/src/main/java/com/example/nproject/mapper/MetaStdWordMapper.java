package com.example.nproject.mapper;

import com.example.nproject.dto.MetaStdWordDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MetaStdWordMapper {
    List<MetaStdWordDto> selectStdWordList(MetaStdWordDto searchDto);

    int countStdWordList(MetaStdWordDto searchDto);
}
