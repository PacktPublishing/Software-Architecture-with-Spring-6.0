package com.packtpub.onlineauction.etlbatchprocess.auctions;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class AuctionItemWriter implements ItemWriter<Auction> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void write(Chunk<? extends Auction> auctions) throws Exception {
        jdbcTemplate.batchUpdate(
                "INSERT INTO auction (orig_id, description, maxbid, minbid, product_id, active, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Auction auction = auctions.getItems().get(i);
                        ps.setString(1, auction.getOriginalId());
                        ps.setString(2, auction.getDescription());
                        ps.setBigDecimal(3, auction.getMaxbid());
                        ps.setBigDecimal(4, auction.getMinbid());
                        ps.setLong(5, auction.getProductid()); // Assuming productid is of type Long
                        ps.setBoolean(6, auction.isActive());
                        ps.setTimestamp(7, Timestamp.valueOf(auction.getCreatedAt()));
                    }

                    @Override
                    public int getBatchSize() {
                        return auctions.size();
                    }
                }
        );
    }

}