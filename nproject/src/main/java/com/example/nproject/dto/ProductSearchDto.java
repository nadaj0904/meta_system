package com.example.nproject.dto;

import java.time.LocalDate;

/**
 * 리스트 화면 검색 조건 DTO
 */
public class ProductSearchDto {
    private String insType;     // 보험사 유형 (L00: 생보, N00: 손보 등)
    private String companyCd;   // 보험사 코드
    private String prdtNm;      // 상품명 (검색어)
    private LocalDate startDate;// 판매시작 검색구간
    private LocalDate endDate;  // 판매종료 검색구간
    
    // Getters and Setters
    public String getInsType() { return insType; }
    public void setInsType(String insType) { this.insType = insType; }

    public String getCompanyCd() { return companyCd; }
    public void setCompanyCd(String companyCd) { this.companyCd = companyCd; }

    public String getPrdtNm() { return prdtNm; }
    public void setPrdtNm(String prdtNm) { this.prdtNm = prdtNm; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
