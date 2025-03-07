package com.packtpub.authenticationservices.config.bootstrap;

import com.packtpub.authenticationservices.adapter.datasources.AuthenticationManagerSecurity;
import com.packtpub.authenticationservices.adapter.datasources.TokenJwt;
import com.packtpub.authenticationservices.adapter.datasources.UserRestApi;
import com.packtpub.authenticationservices.config.security.CustomAuthenticationSuccessHandler;
import com.packtpub.authenticationservices.internal.repositories.AuthenticationManagerRepository;
import com.packtpub.authenticationservices.internal.usecases.GenerateTokenUseCase;
import com.packtpub.authenticationservices.internal.usecases.ValidateTokenUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class BeansConfiguration {


    @Bean
    public GenerateTokenUseCase generateTokenUseCase(UserRestApi userRestApi,
                                                     AuthenticationManager authenticationManager,
                                                     TokenJwt tokenJwt) {
        AuthenticationManagerRepository authenticationManagerRepository = new AuthenticationManagerSecurity(authenticationManager);
        return new GenerateTokenUseCase(authenticationManagerRepository, userRestApi, tokenJwt);
    }

    @Bean
    public ValidateTokenUseCase validateTokenUseCase(TokenJwt tokenJwt) {
        return new ValidateTokenUseCase(tokenJwt);
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler(TokenJwt tokenJwt){
        return new CustomAuthenticationSuccessHandler(tokenJwt);
    }

}