package kr.co.hanbit.product_management.presentation.dto;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import kr.co.hanbit.product_management.domain.Product;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Builder
public class ProductDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer amount;


    // dto -> entity
    public static Product toEntity(ProductDto productDto){
        Product product = Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .amount(productDto.getAmount()).build();

        return product;
    }

    // 엔티티 -> dto
    public static ProductDto toDto(Product product){
        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .amount(product.getAmount()).build();

        return productDto;
    }
}
