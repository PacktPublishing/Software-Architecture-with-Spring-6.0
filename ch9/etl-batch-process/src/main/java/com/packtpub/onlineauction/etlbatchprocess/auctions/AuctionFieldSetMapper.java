package com.packtpub.onlineauction.etlbatchprocess.auctions;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class AuctionFieldSetMapper implements FieldSetMapper<AuctionDto> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    @Override
    public AuctionDto mapFieldSet(final FieldSet fieldSet) throws BindException {
        AuctionDto auctionDto =  AuctionDto.builder()
                .originalId(fieldSet.readString("_id"))
                .description(fieldSet.readString("description"))
                .maxbid(fieldSet.readBigDecimal("maxbid"))
                .minbid(fieldSet.readBigDecimal("minbid"))
                .productid(fieldSet.readLong("productid"))
                .isActive(fieldSet.readBoolean("active"))
//                .createdAt(LocalDateTime.parse(fieldSet.readString("createdat"), DATE_TIME_FORMATTER))
                .createdAt(parseDate(fieldSet.readString("createdat")))
                .build();
        return auctionDto;
    }
    private LocalDateTime parseDate(String dateString) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return offsetDateTime.toLocalDateTime();  // Convert to LocalDateTime if necessary
    }
}
