package kr.co.hanbit.product_management.application;

import kr.co.hanbit.product_management.domain.Product;
import kr.co.hanbit.product_management.infrastructure.ListProductRepository;
import kr.co.hanbit.product_management.presentation.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleProductService {
    private final ListProductRepository listProductRepository;
    private final ModelMapper modelMapper;

    // 상품 추가
    public ProductDto add(ProductDto productDto){
        log.info("\t service add called.....");

        // 1. dto -> domain
        Product product = modelMapper.map(productDto, Product.class);

        // 2. repository 호출
        Product savedProduct = listProductRepository.add(product);

        // 3. domain -> dto
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);

        // 4. dto 반환

        return savedProductDto;
    }

    // 상품 조회
    public ProductDto findById(Long id){
        Product findProduct = listProductRepository.findById(id);

        // entity -> dto
        ProductDto productDto = modelMapper.map(findProduct, ProductDto.class);

        return productDto;
    }
}
