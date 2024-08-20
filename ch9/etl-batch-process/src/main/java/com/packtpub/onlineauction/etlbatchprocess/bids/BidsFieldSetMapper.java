package com.packtpub.onlineauction.etlbatchprocess.bids;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BidsFieldSetMapper implements FieldSetMapper<BidDto> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    @Override
    public BidDto mapFieldSet(final FieldSet fieldSet) throws BindException {
        return BidDto.builder()
                .originalId(fieldSet.readString("_id"))
                .auctionId(fieldSet.readString("auctionId"))
                .userId(fieldSet.readLong("userId"))
                .amount(fieldSet.readBigDecimal("amount"))
                .createdAt(LocalDateTime.parse(fieldSet.readString("createdAt"), DATE_TIME_FORMATTER))
                .build();
    }
}
