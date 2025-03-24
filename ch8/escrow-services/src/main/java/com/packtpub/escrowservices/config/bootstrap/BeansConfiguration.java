package com.packtpub.escrowservices.config.bootstrap;

import com.packtpub.escrowservices.adapter.datasources.escrowAccount.EscrowAccountDatasource;
import com.packtpub.escrowservices.adapter.datasources.escrowAccount.EscrowAccountJpaRepository;
import com.packtpub.escrowservices.adapter.datasources.authentication.AuthenticationRestApi;
import com.packtpub.escrowservices.adapter.datasources.escrowTransaction.EscrowTransactionDatasource;
import com.packtpub.escrowservices.adapter.datasources.escrowTransaction.EscrowTransactionJpaRepository;
import com.packtpub.escrowservices.internal.repositories.EscrowAccountRepository;
import com.packtpub.escrowservices.internal.usecases.CreateEscrowAccountUseCase;
import com.packtpub.escrowservices.internal.usecases.CreateEscrowTransationUseCase;
import com.packtpub.escrowservices.internal.usecases.GetUserEscrowBalanceUseCase;
import com.packtpub.escrowservices.internal.usecases.GetUserEscrowReservationsUseCase;
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
    public CreateEscrowAccountUseCase createEscrowAccountUseCase(EscrowAccountJpaRepository escrowAccountJpaRepository){
        EscrowAccountDatasource escrowAccountDatasource = new EscrowAccountDatasource(escrowAccountJpaRepository);
        return new CreateEscrowAccountUseCase(escrowAccountDatasource);
    }

    @Bean
    public GetUserEscrowBalanceUseCase getUserEscrowBalanceUseCase(EscrowAccountJpaRepository escrowAccountJpaRepository) {
        EscrowAccountDatasource escrowAccountDatasource = new EscrowAccountDatasource(escrowAccountJpaRepository);
        return new GetUserEscrowBalanceUseCase(escrowAccountDatasource);
    }

    @Bean
    public CreateEscrowTransationUseCase createEscrowTransationUseCase(EscrowTransactionJpaRepository escrowTransactionJpaRepository){
        EscrowTransactionDatasource escrowTransactionDatasource = new EscrowTransactionDatasource(escrowTransactionJpaRepository);
        return new CreateEscrowTransationUseCase(escrowTransactionDatasource);
    }

    @Bean
    public GetUserEscrowReservationsUseCase getUserEscrowReservationsUseCase(EscrowTransactionJpaRepository escrowTransactionJpaRepository) {
        EscrowTransactionDatasource escrowAccountDatasource = new EscrowTransactionDatasource(escrowTransactionJpaRepository);
        return new GetUserEscrowReservationsUseCase(escrowAccountDatasource);
    }

    @Bean
    public AuthenticationRestApi authenticationRestApi(RestClient restClient, DiscoveryClient discoveryClient){
        return new AuthenticationRestApi(restClient, discoveryClient);
    }


}