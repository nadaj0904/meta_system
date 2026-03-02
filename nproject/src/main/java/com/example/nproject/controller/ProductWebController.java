package com.example.nproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 보험 상품 페이지 처리를 위한 웹 컨트롤러
 */
@Controller
@RequestMapping("/product")
public class ProductWebController {

    @GetMapping("/list")
    public String productList() {
        return "product/gamk_product_list";
    }
}
