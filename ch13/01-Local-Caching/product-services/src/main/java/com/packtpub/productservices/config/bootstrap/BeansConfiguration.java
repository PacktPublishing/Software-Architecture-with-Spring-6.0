package com.packtpub.productservices.config.bootstrap;

import com.packtpub.productservices.adapter.datasources.authentication.AuthenticationRestApi;
import com.packtpub.productservices.adapter.datasources.product.ProductJpaDatasource;
import com.packtpub.productservices.adapter.datasources.product.ProductJpaRepository;
import com.packtpub.productservices.internal.repositories.ProductRepository;
import com.packtpub.productservices.internal.usecases.AddProductUseCase;
import com.packtpub.productservices.internal.usecases.GetProductsByIdUseCase;
import com.packtpub.productservices.internal.usecases.GetProductsUseCase;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class BeansConfiguration {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

    @Bean
    public AddProductUseCase addProductUseCase(ProductJpaRepository userDatabaseRepository){
        ProductRepository userGateway = new ProductJpaDatasource(userDatabaseRepository);
        return new AddProductUseCase(userGateway);
    }

    @Bean
    public GetProductsByIdUseCase getProductsByIdUseCase(ProductJpaRepository userDatabaseRepository){
        ProductRepository userGateway = new ProductJpaDatasource(userDatabaseRepository);
        return new GetProductsByIdUseCase(userGateway);
    }

    @Bean
    public GetProductsUseCase getProductsUseCase(ProductJpaRepository userDatabaseRepository){
        ProductRepository userGateway = new ProductJpaDatasource(userDatabaseRepository);
        return new GetProductsUseCase(userGateway);
    }

    @Bean
    public AuthenticationRestApi authenticationRestApi(RestClient restClient, DiscoveryClient discoveryClient){
       return new AuthenticationRestApi(restClient, discoveryClient);
    }

}