package com.packtpub.onlineauction.etlbatchprocess.bids;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class BidItemReader extends FlatFileItemReader<BidDto> {

    public BidItemReader(@Value("#{jobParameters['bidsFile']}") String bidsFile) {
        this.setName("BID_READER");
        this.setLinesToSkip(1);
        this.setLineMapper(lineMapper());
        this.setStrict(false);
        this.setResource(new FileSystemResource(bidsFile));
    }

    private LineMapper<BidDto> lineMapper() {
        DefaultLineMapper<BidDto> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("_id", "_class", "amount", "auctionId", "createdAt", "userId");
        lineTokenizer.setStrict(false);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(new BidsFieldSetMapper());
        return defaultLineMapper;
    }

}