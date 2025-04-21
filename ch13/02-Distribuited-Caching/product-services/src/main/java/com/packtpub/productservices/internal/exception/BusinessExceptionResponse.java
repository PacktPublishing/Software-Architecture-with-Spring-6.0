package com.packtpub.productservices.internal.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessExceptionResponse<T> {
    private String code;

    private T data;

    private String message;
}
