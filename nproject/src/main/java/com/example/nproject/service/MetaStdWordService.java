package com.example.nproject.service;

import com.example.nproject.dto.MetaStdWordDto;
import com.example.nproject.mapper.MetaStdWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaStdWordService {

    @Autowired
    private MetaStdWordMapper metaStdWordMapper;

    public List<MetaStdWordDto> getStdWordList(MetaStdWordDto searchDto) {
        return metaStdWordMapper.selectStdWordList(searchDto);
    }

    public int getStdWordCount(MetaStdWordDto searchDto) {
        return metaStdWordMapper.countStdWordList(searchDto);
    }
}
