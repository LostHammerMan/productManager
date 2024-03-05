package kr.co.hanbit.product_management.presentation.controller;

import jakarta.validation.Valid;
import kr.co.hanbit.product_management.application.SimpleProductService;
import kr.co.hanbit.product_management.domain.Product;
import kr.co.hanbit.product_management.presentation.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final SimpleProductService simpleProductService;

    // 상품 추가 요청
    @PostMapping("/products")
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto){
        log.info("controller createProduct called....");
        log.info("\t productDto = {}", productDto);
        simpleProductService.add(productDto);
        return productDto;
    }

    // 상품 단건 조회
    @GetMapping("/products/{id}")
    public ProductDto findProductById(@PathVariable(name = "id") Long id){
        return simpleProductService.findById(id);
    }

    // 상품 전체 조회
//    @GetMapping("/products")
//    public List<ProductDto> findAllProduct(){
//        return simpleProductService.findAll();
//    }

    // 상품 검색 - 이름
    @GetMapping("/products")
    public List<ProductDto> findProducts(@RequestParam(required = false, name = "name") String name){
        if (name == null){
            return simpleProductService.findAll();
        }

        return simpleProductService.findByNameContaining(name);
    }

    // 상품 수정
    @PutMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable(name = "id") Long id, @RequestBody ProductDto productDto){
        productDto.setId(id);

        return simpleProductService.update(productDto);
    }

    // 상품 삭제
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long id){
        simpleProductService.delete(id);
    }
}
