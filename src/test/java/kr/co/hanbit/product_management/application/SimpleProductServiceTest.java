package kr.co.hanbit.product_management.application;

import kr.co.hanbit.product_management.domain.Product;
import kr.co.hanbit.product_management.domain.exception.EntityNotFoundException;
import kr.co.hanbit.product_management.domain.repository_interface.ProductRepository;
import kr.co.hanbit.product_management.presentation.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
//@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("prod")
class SimpleProductServiceTest {

//    @Autowired
    // @Mock 생성된 객체들을 SimpleProductService 에 주입
    @InjectMocks
    SimpleProductService simpleProductService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ValidationService validationService;

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

        assertEquals(savedProductDto.getId(), foundProductDto.getId());
        assertEquals(savedProductDto.getName(), foundProductDto.getName());
        assertEquals(savedProductDto.getPrice(), foundProductDto.getPrice());
        assertEquals(savedProductDto.getAmount(), foundProductDto.getAmount());
    }

    @Test
    @DisplayName("존재하지 않는 상품 id로 조회하면, EntityNotFoundException 이 발생해야 함")
    void findProductNotExist(){

        Long notExistedId = -1L;

        assertThrows(EntityNotFoundException.class,() ->{
            simpleProductService.findById(notExistedId);
        });

    }

    //
    @Test
    @DisplayName("상품 추가 후 추가된 상품 반환")
    void productAddTest(){
        ProductDto productDto = ProductDto.builder()
                .name("찰흙")
                .amount(300)
                .price(5000).build();

        Long PRODUCT_ID = 1L;

        Product product = ProductDto.toEntity(productDto);
        product.setId(PRODUCT_ID);

        when(productRepository.add(any())).thenReturn(product);

        ProductDto savedProductDto = simpleProductService.add(productDto);

        assertTrue(savedProductDto.getId().equals(PRODUCT_ID));
        assertTrue(savedProductDto.getName().equals(productDto.getName()));
        assertTrue(savedProductDto.getAmount().equals(productDto.getAmount()));
        assertTrue(savedProductDto.getPrice().equals(productDto.getPrice()));




    }
}