package com.packtpub.onlineauction.etlbatchprocess.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String city;

    private String state;

    private String country;
}
