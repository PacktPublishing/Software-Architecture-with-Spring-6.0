package com.packtpub.onlineauction.etlbatchprocess.users;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class UserItemWriter implements ItemWriter<User> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void write(Chunk<? extends User> users) throws Exception {
        jdbcTemplate.batchUpdate(
                "INSERT INTO users (id, city, country, email, name, phone_number, state) VALUES (?, ?, ?, ?, ?, ? , ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        User user = users.getItems().get(i);
                        ps.setLong(1, user.getId());
                        ps.setString(2, user.getCity());
                        ps.setString(3, user.getCountry());
                        ps.setString(4, user.getEmail());
                        ps.setString(5, user.getName());
                        ps.setString(6, user.getPhoneNumber());
                        ps.setString(7, user.getState());
                    }

                    @Override
                    public int getBatchSize() {
                        return users.size();
                    }
                }
        );
    }
}