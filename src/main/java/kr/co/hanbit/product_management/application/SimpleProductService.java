package kr.co.hanbit.product_management.application;

import kr.co.hanbit.product_management.domain.Product;
import kr.co.hanbit.product_management.domain.repository_interface.ProductRepository;
import kr.co.hanbit.product_management.infrastructure.DatabaseProductRepository;
import kr.co.hanbit.product_management.infrastructure.ListProductRepository;
import kr.co.hanbit.product_management.presentation.dto.ProductDto;
import kr.co.hanbit.product_management.presentation.dto.ProductMapper;
import kr.co.hanbit.product_management.presentation.dto.ProductMapper2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleProductService {
//    private final ListProductRepository listProductRepository;
    private final ModelMapper modelMapper;
    private final ProductMapper2 productMapper2;
    private final ProductMapper productMapper;
    private final ValidationService validationService;
//    private final DatabaseProductRepository databaseProductRepository;
    private final ProductRepository productRepository;

    // 상품 추가 + validation
    public ProductDto add(ProductDto productDto){
        log.info("service add called.....");
        log.info("\t productDto = {}", productDto);

        // 1. dto -> domain
//        Product product = modelMapper.map(productDto, Product.class);
        Product product = productMapper.MAPPER.toEntity(productDto);
        validationService.checkValid(product);
//        Product product = productMapper.productDtoToProduct(productDto); // 문제 발생
        log.info("product = {}", product);
        // 2. repository 호출
        Product savedProduct = productRepository.add(product);

        // 3. domain -> dto
//        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);
//        ProductDto savedProductDto = productMapper2.productToProductDto(savedProduct);
        ProductDto savedProductDto = productMapper.MAPPER.toDto(savedProduct);
        log.info("savedProductDto = {}", savedProductDto);
        // 4. dto 반환

        return savedProductDto;
    }

    // 상품 단건 조회
    public ProductDto findById(Long id){
        Product findProduct = productRepository.findById(id);

        // entity -> dto
//        ProductDto productDto = modelMapper.map(findProduct, ProductDto.class);
//        ProductDto productDto = productMapper2.productToProductDto(findProduct);
        ProductDto productDto = productMapper.MAPPER.toDto(findProduct);
        return productDto;
    }

    // 전체 상품 조회
    public List<ProductDto> findAll(){
        log.info("service called.......");
        List<Product> products = productRepository.findAll();
        for (Product a : products){
            log.info("products = {}", a);
        }

        // 엔티티 -> dto 변환 후 List 로
//        List<ProductDto> productDtos = products.stream()
//                .map(product -> modelMapper.map(product, ProductDto.class)).toList();
//        List<ProductDto> productDtos = products.stream()
//                .map(product -> productMapper2.productToProductDto(product)).toList();
        List<ProductDto> productDtos = products.stream()
                .map(product -> productMapper.MAPPER.toDto(product))
                .toList();

        for (ProductDto b : productDtos){
            log.info("productDtos = {}", b);
        }
        return productDtos;
    }

    // 상품 검색 - 이름
    public List<ProductDto> findByNameContaining(String name){
        List<Product> findProducts = productRepository.findByNameContaining(name);

        // 엔티티 -> dto
        List<ProductDto> productDtos = findProducts.stream().map(
                product -> productMapper.MAPPER.toDto(product)
        ).toList();

        return productDtos;
    }

    public ProductDto update(ProductDto productDto) {
        log.info("service - updateCalled..........");
        log.info("productDto = {}", productDto);

        // dto -> entity
//        Product product = productMapper2.productDtoToProduct(productDto);
        Product product = productMapper.MAPPER.toEntity(productDto);
        log.info("\t product = {}", product);

        Product updatedProduct = productRepository.update(product);
        log.info("\t updatedProduct ={}", updatedProduct);

        // 엔티티 -> dto
//        ProductDto updatedProductDto = productMapper2.productToProductDto(updatedProduct);
        ProductDto updatedProductDto = productMapper.MAPPER.toDto(updatedProduct);
        log.info("\t updatedProductDto = {}", updatedProductDto);

        return updatedProductDto;

    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
