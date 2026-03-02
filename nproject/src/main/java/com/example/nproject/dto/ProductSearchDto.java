package com.example.nproject.dto;

/**
 * 보험 마케팅 자료 검색 요청 DTO
 */
public class ProductSearchDto {
    private String insuranceTypeCode; // LIFE, NON_LIFE, ALL
    private String companyCode; // SAMSUNG, HANHWA, ALL
    private Long productId; // ALL
    private String materialTypeCode; // SUMMARY, TERMS, ALL

    public String getInsuranceTypeCode() {
        return insuranceTypeCode;
    }

    public void setInsuranceTypeCode(String insuranceTypeCode) {
        this.insuranceTypeCode = insuranceTypeCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getMaterialTypeCode() {
        return materialTypeCode;
    }

    public void setMaterialTypeCode(String materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
    }
}
