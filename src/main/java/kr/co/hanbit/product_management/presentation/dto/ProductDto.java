package kr.co.hanbit.product_management.presentation.dto;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
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


}
