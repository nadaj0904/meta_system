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

    @GetMapping("/gamk_prdt_list")
    public String gamkPrdtList() {
        return "product/gamk_prdt_list";
    }

    @GetMapping("/gamk_prdt_reg")
    public String gamkPrdtReg() {
        return "product/gamk_prdt_reg";
    }

    @GetMapping("/gamk_prdt_detail")
    public String gamkPrdtDetail() {
        return "product/gamk_prdt_detail";
    }

    @GetMapping("/gamk_prdt_mod")
    public String gamkPrdtMod() {
        return "product/gamk_prdt_mod";
    }
}
