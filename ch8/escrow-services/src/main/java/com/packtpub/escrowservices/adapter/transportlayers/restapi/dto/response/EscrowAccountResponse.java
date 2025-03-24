package com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EscrowAccountResponse {

    private Long id;
    private Long userId;
    private BigDecimal availableFunds;
}