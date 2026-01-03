package com.example.nproject.dto;

public class DashboardSummaryDto {
    private int totalSchemas;
    private int totalTables;
    private int totalColumns;
    private int unusedObjects;

    public int getTotalSchemas() {
        return totalSchemas;
    }

    public void setTotalSchemas(int totalSchemas) {
        this.totalSchemas = totalSchemas;
    }

    public int getTotalTables() {
        return totalTables;
    }

    public void setTotalTables(int totalTables) {
        this.totalTables = totalTables;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public int getUnusedObjects() {
        return unusedObjects;
    }

    public void setUnusedObjects(int unusedObjects) {
        this.unusedObjects = unusedObjects;
    }

    @Override
    public String toString() {
        return "DashboardSummaryDto{" +
                "totalSchemas=" + totalSchemas +
                ", totalTables=" + totalTables +
                ", totalColumns=" + totalColumns +
                ", unusedObjects=" + unusedObjects +
                '}';
    }
}
