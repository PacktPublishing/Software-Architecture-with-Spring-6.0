package com.packtpub.onlineauction.etlbatchprocess.users;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserItemProcessor implements ItemProcessor<UserDto, User> {

    public User process(UserDto userDto) throws Exception {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .city(userDto.getCity())
                .state(userDto.getState())
                .country(userDto.getCountry())
                .build();
    }
}