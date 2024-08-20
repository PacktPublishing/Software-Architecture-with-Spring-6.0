package com.packtpub.onlineauction.etlbatchprocess.users;

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
public class UserItemReader extends FlatFileItemReader<UserDto> {

    public UserItemReader(@Value("#{jobParameters['usersFile']}") String usersFile) {
        this.setName("BID_READER");
        this.setLinesToSkip(1);
        this.setLineMapper(lineMapper());
        this.setStrict(false);
        this.setResource(new FileSystemResource(usersFile));
    }

    private LineMapper<UserDto> lineMapper() {
        val defaultLineMapper = new DefaultLineMapper<UserDto>();
        val lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("id","city","country","email","name","phone_number","state");
        lineTokenizer.setStrict(false);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(bidsFieldSetMapper());
        return defaultLineMapper;
    }

    private FieldSetMapper<UserDto> bidsFieldSetMapper() {
        return new UserFieldSetMapper();
    }
}