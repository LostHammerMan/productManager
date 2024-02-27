package kr.co.hanbit.product_management.presentation.dto;

import kr.co.hanbit.product_management.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, Product> {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    @Override
    Product toEntity(final ProductDto dto);

    @Override
    ProductDto toDto(final Product entity);
}
