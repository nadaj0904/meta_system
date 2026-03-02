package com.example.nproject.dto;

/**
 * 제휴 보험사 정보 DTO
 */
public class ProductCompanyDto {
    private Long companyId;
    private String companyCode;
    private String companyName;
    private String companyTypeCode;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTypeCode() {
        return companyTypeCode;
    }

    public void setCompanyTypeCode(String companyTypeCode) {
        this.companyTypeCode = companyTypeCode;
    }
}
