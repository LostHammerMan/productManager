package kr.co.hanbit.product_management.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@ToString
@Getter
@NoArgsConstructor
public class Product {

    private Long id;

    @Size(min = 1, max = 10)
    private String name;

    @Max(1_000_000)
    @Min(0)
    private Integer price;

    @Max(9_999)
    @Min(0)
    private Integer amount;

    @Builder
    public Product(Long id, String name, Integer price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Boolean sameId(Long id){
        return this.id.equals(id);
    }

    public Boolean containsName(String name){
        return this.name.contains(name);
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }

        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        Product product = (Product) obj;
        return Objects.equals(id, product.id);

    }
}
