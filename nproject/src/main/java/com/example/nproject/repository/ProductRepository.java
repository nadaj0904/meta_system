package com.example.nproject.repository;

import com.example.nproject.dto.ProductDto;
import com.example.nproject.dto.ProductSearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductRepository {

    /**
     * 상품 목록 조회 (조회 조건 포함)
     */
    List<ProductDto> selectProductList(ProductSearchDto searchDto);

    /**
     * 특정 상품 상세 조회
     */
    ProductDto selectProductById(String prdtCd);

    /**
     * 상품 데이터 단건 등록
     */
    int insertProduct(ProductDto productDto);

    /**
     * 상품 데이터 수정 (사용여부 등 상태 변경 포함)
     */
    int updateProduct(ProductDto productDto);

    /**
     * 대표상품 목록 조회 (모달 검색용)
     */
    List<ProductDto> selectRepPrdtList(String searchKeyword);
}
