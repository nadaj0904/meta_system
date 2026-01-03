package com.example.nproject.dto;

import java.util.List;
import java.util.ArrayList;

public class SchemaTreeDto {
    private String schemaName;
    private List<String> tables;

    public SchemaTreeDto() {
        this.tables = new ArrayList<>();
    }

    public SchemaTreeDto(String schemaName, List<String> tables) {
        this.schemaName = schemaName;
        this.tables = tables;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }
}
