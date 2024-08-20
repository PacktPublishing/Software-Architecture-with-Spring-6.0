package com.packtpub.onlineauction.etlbatchprocess.products;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class ProductItemWriter implements ItemWriter<Product> {

    private final JdbcTemplate jdbcTemplate;

    public ProductItemWriter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void write(Chunk<? extends Product> products) throws Exception {
        jdbcTemplate.batchUpdate(
                "INSERT INTO product (id, description, name, photo, user_id) VALUES (?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Product product = products.getItems().get(i);
                        ps.setInt(1, product.getId());  // Assuming id is of type Integer
                        ps.setString(2, product.getName());
                        ps.setString(3, product.getDescription());
                        ps.setBytes(4, product.getPhoto());  // Assuming photo is stored as a byte array
                        ps.setInt(5, product.getUserId());  // Assuming userId is of type Integer
                    }

                    @Override
                    public int getBatchSize() {
                        return products.size();
                    }
                }
        );
    }

}