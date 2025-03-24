package com.packtpub.notificationservices.config.bootstrap;

import com.packtpub.notificationservices.adapter.datasources.authentication.AuthenticationRestApi;
import com.packtpub.notificationservices.adapter.datasources.notification.NotificationDatasource;
import com.packtpub.notificationservices.adapter.datasources.notification.NotificationDocumentRepository;
import com.packtpub.notificationservices.internal.repositories.NotificationRepository;
import com.packtpub.notificationservices.internal.usecases.CreateNotificationUseCase;
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
    public AuthenticationRestApi authenticationRestApi(RestClient restClient, DiscoveryClient discoveryClient){
        return new AuthenticationRestApi(restClient, discoveryClient);
    }

    @Bean
    public CreateNotificationUseCase createNotificationUseCase(NotificationDocumentRepository notificationDocumentRepository){
        NotificationDatasource notificationDatasource = new NotificationDatasource(notificationDocumentRepository);
        return new CreateNotificationUseCase(notificationDatasource);
    }

}