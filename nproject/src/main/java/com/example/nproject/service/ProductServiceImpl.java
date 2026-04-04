package com.example.nproject.service;

import com.example.nproject.dto.ProductDto;
import com.example.nproject.dto.ProductSearchDto;
import com.example.nproject.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getProductList(ProductSearchDto searchDto) {
        log.info("Fetching Product List with search conditions: {}", searchDto);
        return productRepository.selectProductList(searchDto);
    }

    @Override
    public ProductDto getProductById(String prdtCd) {
        log.info("Fetching Product details for ID: {}", prdtCd);
        return productRepository.selectProductById(prdtCd);
    }

    @Transactional
    @Override
    public void registerProduct(ProductDto productDto) {
        log.info("Registering new product: {}", productDto.getPrdtCd());
        // 추가 Validation 로직 필요시 이곳에 삽입 (e.g. 중복 키 검사)
        
        int result = productRepository.insertProduct(productDto);
        if (result == 0) {
            throw new RuntimeException("상품 등록 데이터 저장에 실패했습니다.");
        }
    }

    @Transactional
    @Override
    public void modifyProduct(ProductDto productDto) {
        log.info("Modifying product: {}", productDto.getPrdtCd());
        
        int result = productRepository.updateProduct(productDto);
        if (result == 0) {
            throw new RuntimeException("상품 데이터 변경에 실패했습니다. (존재하지 않는 코드일 수 있습니다.)");
        }
    }

    @Override
    public List<ProductDto> getRepProductList(String searchKeyword) {
        log.info("Fetching Representative Product List with keyword: {}", searchKeyword);
        return productRepository.selectRepPrdtList(searchKeyword);
    }
}
