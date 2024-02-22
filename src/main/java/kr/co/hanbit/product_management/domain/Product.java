package kr.co.hanbit.product_management.domain;

import lombok.Getter;


public class Product {

    private Long id;
    private String name;
    private Integer price;
    private Integer amount;

    public Boolean sameId(Long id){
        return this.id.equals(id);
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
