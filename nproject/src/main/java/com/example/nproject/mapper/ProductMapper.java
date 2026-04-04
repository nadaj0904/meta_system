package com.example.nproject.mapper;

import com.example.nproject.dto.ProductCompanyDto;
import com.example.nproject.dto.ProductMasterDto;
import com.example.nproject.dto.ProductMaterialDto;
import com.example.nproject.dto.ProductSearchDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 보험 상품 및 마케팅 자료 처리를 위한 Mapper 인터페이스
 */
@Mapper
public interface ProductMapper {

    /**
     * 보험사 목록 조회
     *
     * @param insuranceTypeCode 생명보험/손해보험 구분 코드 (선택)
     * @return 보험사 목록
     */
    List<ProductCompanyDto> selectCompanyList(@Param("insuranceTypeCode") String insuranceTypeCode);

    /**
     * 특정 보험사의 상품 목록 조회
     *
     * @param companyCode 보험사 코드
     * @return 상품 목록
     */
    List<ProductMasterDto> selectProductList(@Param("companyCode") String companyCode);

    /**
     * 마케팅 자료 유형 목록 조회
     *
     * @return 자료 유형 목록 (code, name 매핑)
     */
    List<Map<String, Object>> selectMaterialTypeList();

    /**
     * 조건에 맞는 마케팅 자료 목록 조회
     *
     * @param searchDto 검색 조건
     * @return 마케팅 자료 화면용 DTO 목록
     */
    List<ProductMaterialDto> selectMaterialList(ProductSearchDto searchDto);

    /**
     * 대표상품 목록 조회 (모달 검색용)
     *
     * @param searchKeyword 검색어 (null 가능)
     * @return 대표상품 DTO 목록
     */
    List<com.example.nproject.dto.ProductDto> selectRepPrdtList(@Param("searchKeyword") String searchKeyword);
}
