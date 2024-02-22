package kr.co.hanbit.product_management.infrastructure;

import kr.co.hanbit.product_management.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Slf4j
public class ListProductRepository {

    private List<Product> products = new CopyOnWriteArrayList<>();

    private AtomicLong sequence = new AtomicLong(1L);

    // 추가
    public Product add(Product product){
        log.info("repository add called......");
        product.setId(sequence.getAndAdd(1L));
        products.add(product);
        log.info("product = {}", product.getId());

        return product;
    }

    // 상품번호 기준 조회
    public Product findById(Long id){
        return products.stream().filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow(() ->new RuntimeException("찾으려는 상품이 존재하지 않습니다"));
    }



}
