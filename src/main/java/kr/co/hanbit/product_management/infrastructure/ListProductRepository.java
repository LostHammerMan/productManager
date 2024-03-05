package kr.co.hanbit.product_management.infrastructure;

import kr.co.hanbit.product_management.domain.Product;
import kr.co.hanbit.product_management.domain.exception.EntityNotFoundException;
import kr.co.hanbit.product_management.domain.repository_interface.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Slf4j
@Profile("test")
public class ListProductRepository implements ProductRepository {

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
                .orElseThrow(() ->new EntityNotFoundException("찾으려는 상품이 존재하지 않습니다"));
    }

    // 전체 상품 조회
    public List<Product> findAll(){
        return products;
    }

    // 상품 검색 - 이름
    public List<Product> findByNameContaining(String name){
        return products.stream().filter(product -> product.containsName(name)).toList();
    }


    public Product update(Product product) {
        log.info("repository called........");
        log.info("\t product = {}", product);
        Integer indexToModify = products.indexOf(product);
        log.info("\t indexToModify = {}", indexToModify);

        products.set(indexToModify, product);

        return product;
    }

    public void delete(Long id) {
        Product findProduct = this.findById(id);
        products.remove(findProduct);
    }
}
