package com.example.nproject.controller;

import com.example.nproject.dto.ProductCompanyDto;
import com.example.nproject.dto.ProductMasterDto;
import com.example.nproject.dto.ProductMaterialDto;
import com.example.nproject.dto.ProductSearchDto;
import com.example.nproject.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 보험 상품 리스트 페이지 REST API 컨트롤러
 */
@RestController
@RequestMapping("/api/products")
public class ProductApiController {

    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 보험사 목록을 반환합니다.
     * 
     * @param insuranceTypeCode 생명/손해보험 필터링용 (ALL이면 전체)
     * @return 보험사 목록
     */
    @GetMapping("/companies")
    public List<ProductCompanyDto> getCompanies(
            @RequestParam(required = false, defaultValue = "ALL") String insuranceTypeCode) {
        return productService.getCompanyList(insuranceTypeCode);
    }

    /**
     * 특정 보험사의 상품 목록을 반환합니다.
     * 
     * @param companyCode 보험사 코드 (ALL이면 전체)
     * @return 상품 목록
     */
    @GetMapping("/list")
    public List<ProductMasterDto> getProducts(
            @RequestParam(required = false, defaultValue = "ALL") String companyCode) {
        return productService.getProductList(companyCode);
    }

    /**
     * 마케팅 자료 유형 목록(상품요약서, 약관 등)을 반환합니다.
     * 
     * @return 자료 유형 목록
     */
    @GetMapping("/material-types")
    public List<Map<String, Object>> getMaterialTypes() {
        return productService.getMaterialTypeList();
    }

    /**
     * 선택된 조건에 맞는 마케팅 자료 리스트를 검색하여 반환합니다.
     * 
     * @param searchDto 검색 매개변수 (JSON)
     * @return 자료 화면용 DTO 목록
     */
    @PostMapping("/search")
    public List<ProductMaterialDto> searchMaterials(@RequestBody ProductSearchDto searchDto) {
        return productService.searchMaterialList(searchDto);
    }
}
