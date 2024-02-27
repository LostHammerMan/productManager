package kr.co.hanbit.product_management.presentation.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class ProductDto {

    private Long id;
    private String name;
    private Integer price;
    private Integer amount;
}
