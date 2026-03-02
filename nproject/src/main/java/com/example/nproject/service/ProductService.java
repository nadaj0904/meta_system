package com.example.nproject.service;

import com.example.nproject.dto.ProductCompanyDto;
import com.example.nproject.dto.ProductMasterDto;
import com.example.nproject.dto.ProductMaterialDto;
import com.example.nproject.dto.ProductSearchDto;
import com.example.nproject.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 보험 상품 및 마케팅 자료 처리를 위한 Service 계층
 */
@Service
public class ProductService {

    private final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    /**
     * 보험사 목록 조회
     * 
     * @param insuranceTypeCode 생명/손해 구분 코드
     * @return 보험사 DTO 목록
     */
    public List<ProductCompanyDto> getCompanyList(String insuranceTypeCode) {
        return productMapper.selectCompanyList(insuranceTypeCode);
    }

    /**
     * 특정 보험사의 상품 목록 조회
     * 
     * @param companyCode 보험사 코드
     * @return 상품 DTO 목록
     */
    public List<ProductMasterDto> getProductList(String companyCode) {
        return productMapper.selectProductList(companyCode);
    }

    /**
     * 자료 유형(상품요약서, 약관 등) 목록 조회
     * 
     * @return 자료 유형 Map 리스트
     */
    public List<Map<String, Object>> getMaterialTypeList() {
        return productMapper.selectMaterialTypeList();
    }

    /**
     * 조건에 맞는 마케팅 자료 목록 조회
     * 
     * @param searchDto 조회 조건 DTO
     * @return 자료 화면용 DTO 리스트
     */
    public List<ProductMaterialDto> searchMaterialList(ProductSearchDto searchDto) {
        return productMapper.selectMaterialList(searchDto);
    }
}
