package com.packtpub.onlineauction.etlbatchprocess.bids;

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
public class BidItemWriter implements ItemWriter<Bid> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void write(Chunk<? extends Bid> bids) throws Exception {
        jdbcTemplate.batchUpdate(
                "INSERT INTO bid (orig_id, amount, user_id, auction_id, created_at) VALUES (?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Bid bid = bids.getItems().get(i);
                        ps.setString(1, bid.getOriginalId());
                        ps.setBigDecimal(2, bid.getAmount());
                        ps.setLong(3, bid.getUserId());
                        ps.setString(4, bid.getAuctionId());
                        ps.setTimestamp(5, Timestamp.valueOf(bid.getCreatedAt()));
                    }

                    @Override
                    public int getBatchSize() {
                        return bids.size();
                    }
                }
        );
    }

//    @Override
//    public void write(Chunk<? extends Bid> bids) throws Exception {
//        jdbcTemplate.batchUpdate(
//                "INSERT INTO bid (orig_id, amount, user_id, auction_id, created_at) VALUES (?, ?, ?, ?, ?)",
//                new BatchPreparedStatementSetter() {
//                    @Override
//                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//                         bids.getItems().forEach(n -> {
//                            try {
//                                ps.setString(1, n.getOriginalId());
//                                ps.setBigDecimal(2, n.getAmount());
//                                ps.setLong(3, n.getUserId());
//                                ps.setString(4, n.getAuctionId());
//                                ps.setTimestamp(5, Timestamp.valueOf(n.getCreatedAt()));
//                            } catch (SQLException e) {
//                                throw new RuntimeException(e);
//                            }
//                        });
//
//                    }
//
//                    @Override
//                    public int getBatchSize() {
//                        return bids.size();
//                    }
//                }
//        );
//    }

//    @Override
//    public void write(Chunk<? extends Bid> bids) throws Exception {
//        jdbcTemplate.batchUpdate(
//                "INSERT INTO bid (orig_id, amount, user_id, auction_id, created_at) VALUES (?, ?, ?)",
//                new BatchPreparedStatementSetter() {
//                    @Override
//                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//                        Bid bid = bids.getItems().get(i);
//
//                        ps.setString(1, bid.getOriginalId());
//                        ps.setBigDecimal(2, bid.getAmount());
//                        ps.setLong(3, bid.getUserId());
//                        ps.setString(4, bid.getAuctionId());
//                    });
//
//                }
//
//        @Override
//        public int getBatchSize () {
//            return bids.size();
//        }
//    }
//        );
//}
}