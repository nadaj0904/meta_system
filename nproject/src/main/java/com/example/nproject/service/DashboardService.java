package com.example.nproject.service;

import com.example.nproject.dto.DashboardSummaryDto;
import com.example.nproject.mapper.MetaStdDashboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private MetaStdDashboardMapper metaStdDashboardMapper;

    public DashboardSummaryDto getDashboardSummary() {
        return metaStdDashboardMapper.selectDashboardSummary();
    }

    public java.util.List<com.example.nproject.dto.SchemaTreeDto> getSchemaTableTree() {
        java.util.List<java.util.Map<String, String>> flatList = metaStdDashboardMapper.selectSchemaTableList();

        java.util.Map<String, java.util.List<String>> grouped = new java.util.HashMap<>();
        for (java.util.Map<String, String> row : flatList) {
            String schema = row.get("schemaName");
            String table = row.get("tableName");
            grouped.computeIfAbsent(schema, k -> new java.util.ArrayList<>()).add(table);
        }

        java.util.List<com.example.nproject.dto.SchemaTreeDto> tree = new java.util.ArrayList<>();
        for (java.util.Map.Entry<String, java.util.List<String>> entry : grouped.entrySet()) {
            tree.add(new com.example.nproject.dto.SchemaTreeDto(entry.getKey(), entry.getValue()));
        }
        return tree;
    }

    public com.example.nproject.dto.TableDetailDto getTableDetails(String schema, String table) {
        return metaStdDashboardMapper.selectTableDetails(schema, table);
    }
}
