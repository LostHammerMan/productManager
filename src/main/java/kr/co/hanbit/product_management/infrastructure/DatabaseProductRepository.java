package kr.co.hanbit.product_management.infrastructure;

import kr.co.hanbit.product_management.domain.Product;
import kr.co.hanbit.product_management.domain.repository_interface.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
@Profile("prod")
public class DatabaseProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Product add(Product product){

        //  id 를 넣기 위해 사용
        KeyHolder keyHolder = new GeneratedKeyHolder();

        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(product);
        namedParameterJdbcTemplate
                .update("INSERT INTO product_management.products (name, price, amount) VALUES (:name, :price, :amount)",
                        namedParameter, keyHolder);

        Long generatedId = keyHolder.getKey().longValue();
        product.setId(generatedId);


        return product;
    }

    public Product findById(Long id){

        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);

        Product product = namedParameterJdbcTemplate.queryForObject(
                "SELECT id, name, price, amount FROM product_management.products WHERE id = :id",
                namedParameter, new BeanPropertyRowMapper<>(Product.class)
        );

        return product;
    }

    public List<Product> findAll(){
        log.info("repository - findAll called.......");

        List<Product> products = namedParameterJdbcTemplate.query(
                "SELECT * FROM product_management.products", new BeanPropertyRowMapper<>(Product.class)
        );
        log.info("products = {}", products);

        return products;
    }

    public List<Product> findByNameContaining(String name){
        log.info("repository - findByNameContaining called....");

        SqlParameterSource namedParameter = new MapSqlParameterSource("name", "%" + name + "%");

        List<Product> productList = namedParameterJdbcTemplate.query(
                "SELECT * FROM product_management.products WHERE name LIKE :name"
                , namedParameter, new BeanPropertyRowMapper<>(Product.class)
        );

        return productList;
    }

    public Product update(Product product){

        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(product);

        namedParameterJdbcTemplate.update(
                "UPDATE product_management.products SET name=:name, price=:price, amount=:amount WHERE id=:id"
                ,namedParameter
        );

        return product;
    }

    public void delete(Long id){

        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);

        namedParameterJdbcTemplate.update(
                "DELETE FROM product_management.products WHERE id=:id", namedParameter
        );
        log.info("repository - 삭제 성공");
    }
}
