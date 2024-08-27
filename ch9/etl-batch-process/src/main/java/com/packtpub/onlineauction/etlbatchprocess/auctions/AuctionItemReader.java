package com.packtpub.onlineauction.etlbatchprocess.auctions;

import lombok.val;
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
public class AuctionItemReader extends FlatFileItemReader<AuctionDto> {

    public AuctionItemReader(@Value("#{jobParameters['auctionsFile']}") String auctionsFile) {
        this.setName("AUCTION_READER");
        this.setLinesToSkip(1);
        this.setLineMapper(lineMapper());
        this.setStrict(false);
        this.setResource(new FileSystemResource(auctionsFile));
    }

    private LineMapper<AuctionDto> lineMapper() {
        val defaultLineMapper = new DefaultLineMapper<AuctionDto>();
        val lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("_id","_class","createdat","active","description","maxbid","minbid","productid");
        lineTokenizer.setStrict(false);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(auctionsFieldSetMapper());
        return defaultLineMapper;
    }

    private FieldSetMapper<AuctionDto> auctionsFieldSetMapper() {
        return new AuctionFieldSetMapper();
    }
}