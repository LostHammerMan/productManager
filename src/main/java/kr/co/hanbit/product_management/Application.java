package kr.co.hanbit.product_management;

import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(DataSource dataSource) throws SQLException {

        return args -> {
            Connection connection = dataSource.getConnection();
        };
    }

}

