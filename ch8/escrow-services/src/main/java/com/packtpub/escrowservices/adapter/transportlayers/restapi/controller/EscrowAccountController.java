package com.packtpub.escrowservices.adapter.transportlayers.restapi.controller;

import com.packtpub.escrowservices.adapter.transportlayers.restapi.EscrowAccountMapper;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.request.EscrowAccountRequest;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.response.EscrowAccountResponse;
import com.packtpub.escrowservices.internal.entity.EscrowAccount;
import com.packtpub.escrowservices.internal.usecases.CreateEscrowAccountUseCase;
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
@RequestMapping("/v1/escrowAccounts")
public class EscrowAccountController {

    private final CreateEscrowAccountUseCase createEscrowAccountUseCase;

    @Operation(summary = "Create an escrow account", description = "Create an escrow account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EscrowAccountResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public EscrowAccountResponse create(@RequestBody EscrowAccountRequest escrowAccountRequest) {

        EscrowAccount escrowAccount = EscrowAccountMapper.INSTANCE.map(escrowAccountRequest);
        escrowAccount.setCreatedAt(escrowAccountRequest.getCreatedAt());
        EscrowAccount escrowAccount1 = createEscrowAccountUseCase.execute(escrowAccount);
        return EscrowAccountMapper.INSTANCE.map(escrowAccount1);

    }

}