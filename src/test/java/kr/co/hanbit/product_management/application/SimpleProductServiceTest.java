package kr.co.hanbit.product_management.application;

import kr.co.hanbit.product_management.presentation.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("prod")
class SimpleProductServiceTest {

    @Autowired
    SimpleProductService simpleProductService;

    @Test
    @DisplayName("상품 추가 후 id로 조회하면 해당 상품이 조회되어야 함")
    void productAndFindByIdTest(){

        ProductDto productDto = ProductDto.builder()
                .name("연필")
                .price(20)
                .amount(300).build();

        ProductDto savedProductDto = simpleProductService.add(productDto);
        Long savedProductId = savedProductDto.getId();

        ProductDto foundProductDto = simpleProductService.findById(savedProductId);

        log.info(String.valueOf(savedProductDto.getId().equals(foundProductDto.getId())));
        log.info(String.valueOf(savedProductDto.getName().equals(foundProductDto.getName())));
        log.info(String.valueOf(savedProductDto.getPrice().equals(foundProductDto.getPrice())));
        log.info(String.valueOf(savedProductDto.getAmount().equals(foundProductDto.getAmount())));
    }
}