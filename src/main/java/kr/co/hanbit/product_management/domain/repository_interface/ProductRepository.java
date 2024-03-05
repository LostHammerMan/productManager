package kr.co.hanbit.product_management.domain.repository_interface;

import kr.co.hanbit.product_management.domain.Product;

import java.util.List;

public interface ProductRepository {

    Product add(Product product);
    Product findById(Long id);
    List<Product> findAll();
    List<Product> findByNameContaining(String name);
    Product update(Product product);
    void delete(Long id);
}
