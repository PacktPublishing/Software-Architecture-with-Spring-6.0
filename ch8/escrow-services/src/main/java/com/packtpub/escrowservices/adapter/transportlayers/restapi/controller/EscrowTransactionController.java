package com.packtpub.escrowservices.adapter.transportlayers.restapi.controller;

import com.packtpub.escrowservices.adapter.datasources.escrowTransaction.kafka.BidConsumer;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.EscrowTransactionMapper;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.request.EscrowTransactionRequest;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.response.EscrowTransactionResponse;
import com.packtpub.escrowservices.internal.entity.EscrowTransaction;
import com.packtpub.escrowservices.internal.usecases.CreateEscrowTransationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/escrowTransactions")
public class EscrowTransactionController {

    private final CreateEscrowTransationUseCase createEscrowTransationUseCase;
    private final BidConsumer bidConsumer;


    @Operation(summary = "Create an escrow transaction", description = "Create an escrow transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EscrowTransactionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public EscrowTransactionResponse create(@RequestBody EscrowTransactionRequest escrowTransactionRequest) {

        EscrowTransaction escrowTransaction = EscrowTransactionMapper.INSTANCE.map(escrowTransactionRequest);
        EscrowTransaction escrowTransaction1 = createEscrowTransationUseCase.execute(escrowTransaction);
        return EscrowTransactionMapper.INSTANCE.map(escrowTransaction1);

    }

}