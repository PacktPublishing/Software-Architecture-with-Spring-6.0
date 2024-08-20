package com.packtpub.onlineauction.etlbatchprocess.products;

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
public class ProductItemReader extends FlatFileItemReader<ProductDto> {

    public ProductItemReader(@Value("#{jobParameters['productsFile']}") String productsFile) {
        this.setName("BID_READER");
        this.setLinesToSkip(1);
        this.setLineMapper(lineMapper());
        this.setStrict(false);
        this.setResource(new FileSystemResource(productsFile));
    }

    private LineMapper<ProductDto> lineMapper() {
        val defaultLineMapper = new DefaultLineMapper<ProductDto>();
        val lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("id","description","name","photo","user_id");
        lineTokenizer.setStrict(false);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(bidsFieldSetMapper());
        return defaultLineMapper;
    }

    private FieldSetMapper<ProductDto> bidsFieldSetMapper() {
        return new ProductFieldSetMapper();
    }
}