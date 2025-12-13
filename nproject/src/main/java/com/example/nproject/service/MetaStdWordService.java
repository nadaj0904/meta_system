package com.example.nproject.service;

import com.example.nproject.dto.MetaStdWordDto;
import com.example.nproject.mapper.MetaStdInsertMapper;
import com.example.nproject.mapper.MetaStdWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaStdWordService {

    @Autowired
    private MetaStdWordMapper metaStdWordMapper;

    @Autowired
    private MetaStdInsertMapper metaStdInsertMapper;

    public List<MetaStdWordDto> getStdWordList(MetaStdWordDto searchDto) {
        return metaStdWordMapper.selectStdWordList(searchDto);
    }

    public int getStdWordCount(MetaStdWordDto searchDto) {
        return metaStdWordMapper.countStdWordList(searchDto);
    }

    public String registerStdWord(MetaStdWordDto dto) {
        // 1. Check for duplicates
        int count = metaStdInsertMapper.countByStdWordName(dto.getStdWordName());
        if (count > 0) {
            return "DUPLICATE";
        }

        // 2. Insert
        metaStdInsertMapper.insertStdWord(dto);
        return "SUCCESS";
    }
}
