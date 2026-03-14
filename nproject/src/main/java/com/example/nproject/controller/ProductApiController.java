package com.example.nproject.controller;

import com.example.nproject.dto.ProductDto;
import com.example.nproject.dto.ProductSearchDto;
import com.example.nproject.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 보험사 상품 등록/수정/조회를 위한 REST API 컨트롤러
 */
@RestController
@RequestMapping("/api/prdt")
public class ProductApiController {

    private static final Logger log = LoggerFactory.getLogger(ProductApiController.class);

    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 상품 목록 조회 (필터 검색 결합)
     */
    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getProductList(@ModelAttribute ProductSearchDto searchDto) {
        log.info("API request to fetch product list with query: {}", searchDto);
        List<ProductDto> list = productService.getProductList(searchDto);
        return ResponseEntity.ok(list);
    }

    /**
     * 상품 상세 단건 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductDetail(@PathVariable("id") String prdtCd) {
        ProductDto product = productService.getProductById(prdtCd);
        return ResponseEntity.ok(product);
    }

    /**
     * 신규 상품 데이터 등록
     */
    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody ProductDto productDto) {
        log.info("API request to register product: {}", productDto);
        try {
            productService.registerProduct(productDto);
            return ResponseEntity.ok(Map.of("success", true, "message", "등록 성공"));
        } catch (Exception e) {
            log.error("Register Error: ", e);
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    /**
     * 기존 상품 데이터 수정/삭제(상태변경)
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> modifyProduct(@PathVariable("id") String prdtCd, @RequestBody ProductDto productDto) {
        log.info("API request to update product: {}", prdtCd);
        // Path Variable 검증 및 바인딩
        if (productDto.getPrdtCd() == null || !productDto.getPrdtCd().equals(prdtCd)) {
            productDto.setPrdtCd(prdtCd);
        }

        try {
            productService.modifyProduct(productDto);
            return ResponseEntity.ok(Map.of("success", true, "message", "수정 성공"));
        } catch (Exception e) {
            log.error("Update Error: ", e);
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }
}
