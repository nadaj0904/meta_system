package com.example.nproject.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProductDto {
    private String prdtCd;
    private String coalCoCd;
    private String repPrdtCd;
    private String prdtNm;
    private String prdtDesc;
    private LocalDate saleStartDate;
    private LocalDate saleEndDate;
    private String useYn;
    private String createdId;
    private LocalDateTime createdAt;
    private String updatedId;
    private LocalDateTime updatedAt;
    
    // 조인용 필드
    private String repPrdtNm;
    private String coalCoNm; // 보험사 명칭

    // Getters and Setters
    public String getPrdtCd() { return prdtCd; }
    public void setPrdtCd(String prdtCd) { this.prdtCd = prdtCd; }
    
    public String getCoalCoCd() { return coalCoCd; }
    public void setCoalCoCd(String coalCoCd) { this.coalCoCd = coalCoCd; }
    
    public String getRepPrdtCd() { return repPrdtCd; }
    public void setRepPrdtCd(String repPrdtCd) { this.repPrdtCd = repPrdtCd; }
    
    public String getPrdtNm() { return prdtNm; }
    public void setPrdtNm(String prdtNm) { this.prdtNm = prdtNm; }
    
    public String getPrdtDesc() { return prdtDesc; }
    public void setPrdtDesc(String prdtDesc) { this.prdtDesc = prdtDesc; }
    
    public LocalDate getSaleStartDate() { return saleStartDate; }
    public void setSaleStartDate(LocalDate saleStartDate) { this.saleStartDate = saleStartDate; }
    
    public LocalDate getSaleEndDate() { return saleEndDate; }
    public void setSaleEndDate(LocalDate saleEndDate) { this.saleEndDate = saleEndDate; }
    
    public String getUseYn() { return useYn; }
    public void setUseYn(String useYn) { this.useYn = useYn; }
    
    public String getCreatedId() { return createdId; }
    public void setCreatedId(String createdId) { this.createdId = createdId; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public String getUpdatedId() { return updatedId; }
    public void setUpdatedId(String updatedId) { this.updatedId = updatedId; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getRepPrdtNm() { return repPrdtNm; }
    public void setRepPrdtNm(String repPrdtNm) { this.repPrdtNm = repPrdtNm; }

    public String getCoalCoNm() { return coalCoNm; }
    public void setCoalCoNm(String coalCoNm) { this.coalCoNm = coalCoNm; }
}
