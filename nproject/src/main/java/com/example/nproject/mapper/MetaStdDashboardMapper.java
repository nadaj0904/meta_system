package com.example.nproject.mapper;

import com.example.nproject.dto.DashboardSummaryDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MetaStdDashboardMapper {
    DashboardSummaryDto selectDashboardSummary();

    java.util.List<java.util.Map<String, String>> selectSchemaTableList();

    com.example.nproject.dto.TableDetailDto selectTableDetails(
            @org.apache.ibatis.annotations.Param("schema") String schema,
            @org.apache.ibatis.annotations.Param("table") String table);
}
