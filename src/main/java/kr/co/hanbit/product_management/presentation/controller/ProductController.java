package kr.co.hanbit.product_management.presentation.controller;

import kr.co.hanbit.product_management.application.SimpleProductService;
import kr.co.hanbit.product_management.domain.Product;
import kr.co.hanbit.product_management.presentation.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final SimpleProductService simpleProductService;

    // 상품 추가 요청
    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        log.info("controller createProduct called....");
        simpleProductService.add(productDto);
        return productDto;
    }

    // 상품 조회
    @GetMapping("/products/{id}")
    public ProductDto findProductById(@PathVariable(name = "id") Long id){
        return simpleProductService.findById(id);
    }
}
