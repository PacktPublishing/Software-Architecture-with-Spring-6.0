package com.packtpub.authenticationservices.config.exceptions;

import com.packtpub.authenticationservices.internal.exceptions.BusinessException;
import com.packtpub.authenticationservices.internal.exceptions.BusinessExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

@RestControllerAdvice
@Component
public class GlobalHandler {

    @ExceptionHandler(BusinessException.class)
    public Mono<BusinessExceptionResponse> handleBusinessException(BusinessException e) {
        BusinessExceptionResponse response = new BusinessExceptionResponse(
                e.getCode(),
                null,
                e.getLocalizedMessage()
        );
        return Mono.just(response);
    }

    @ExceptionHandler(Exception.class)
    public Mono<AtomicReference<ProblemDetail>> handleException(Exception exception, ServerHttpResponse response) {
        AtomicReference<ProblemDetail> errorDetail = null;

        return Mono.fromCallable(() -> {
            switch (exception) {
                case BadCredentialsException ex -> {
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    errorDetail.set(ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.getMessage()));
                    errorDetail.get().setProperty("description", "The username or password is incorrect");
                }
                case AccountStatusException ex -> {
                    response.setStatusCode(HttpStatus.FORBIDDEN);
                    errorDetail.set(ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.getMessage()));
                    errorDetail.get().setProperty("description", "The account is locked");
                }
                case AccessDeniedException ex -> {
                    response.setStatusCode(HttpStatus.FORBIDDEN);
                    errorDetail.set(ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.getMessage()));
                    errorDetail.get().setProperty("description", "You are not authorized to access this resource");
                }
//                case SignatureException ex, ExpiredJwtException ex -> {
//                    response.setStatusCode(HttpStatus.FORBIDDEN);
//                    errorDetail.set(ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.getMessage()));
//                    errorDetail.get().setProperty("description", "The JWT signature is invalid");
//                }
                default -> {
                    response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                    errorDetail.set(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
                    errorDetail.get().setProperty("description", "Unknown internal server error.");
                }
            }
            return errorDetail;
        });
    }
}