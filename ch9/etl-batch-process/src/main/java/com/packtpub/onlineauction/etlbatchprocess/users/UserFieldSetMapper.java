package com.packtpub.onlineauction.etlbatchprocess.users;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserFieldSetMapper implements FieldSetMapper<UserDto> {

    @Override
    public UserDto mapFieldSet(final FieldSet fieldSet) throws BindException {
        return UserDto.builder()
                .id(fieldSet.readLong("id"))
                .name(fieldSet.readString("name"))
                .email(fieldSet.readString("email"))
                .phoneNumber(fieldSet.readString("phone_number"))
                .city(fieldSet.readString("city"))
                .state(fieldSet.readString("state"))
                .country(fieldSet.readString("country"))
                .build();
    }
}
