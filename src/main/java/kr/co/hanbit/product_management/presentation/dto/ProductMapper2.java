package kr.co.hanbit.product_management.presentation.dto;

import kr.co.hanbit.product_management.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper2 {

    ProductMapper2 INSTANCE = Mappers.getMapper(ProductMapper2.class);
    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto); // 문제 발생
//    Product product = productMapper.productDtoToProduct(productDto); // 문제 발생
    // dto ->
//    @Mapper
//    public interface MessageMapper {
//        MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);
//
//        // RequestDto -> MessageBodyDto 매핑
//        MessageBodyDto toMessageBodyDto(RequestDto requestDto);
//    }
}
