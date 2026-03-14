package com.example.nproject.service;

import com.example.nproject.dto.ProductDto;
import com.example.nproject.dto.ProductSearchDto;

import java.util.List;

public interface ProductService {

    /**
     * 상품 목록 조회 (조회 조건 적용)
     */
    List<ProductDto> getProductList(ProductSearchDto searchDto);

    /**
     * 개별 상품 상세 조회
     */
    ProductDto getProductById(String prdtCd);

    /**
     * 새로운 상품 등록
     */
    void registerProduct(ProductDto productDto);

    /**
     * 기존 상품 정보 업데이트 (사용여부 미사용 처리 등 포함)
     */
    void modifyProduct(ProductDto productDto);
}
