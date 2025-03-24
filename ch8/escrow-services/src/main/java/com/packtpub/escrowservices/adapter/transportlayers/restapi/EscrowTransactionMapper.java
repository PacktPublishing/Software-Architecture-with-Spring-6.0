package com.packtpub.escrowservices.adapter.transportlayers.restapi;

import com.packtpub.escrowservices.adapter.datasources.escrowTransaction.EscrowTransactionEntity;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.event.EscrowEvent;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.request.EscrowTransactionRequest;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.response.EscrowTransactionResponse;
import com.packtpub.escrowservices.internal.entity.EscrowTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EscrowTransactionMapper {
    EscrowTransactionMapper INSTANCE = Mappers.getMapper(EscrowTransactionMapper.class);

    EscrowTransaction map(EscrowTransactionRequest escrowTransactionRequest);
    EscrowTransactionResponse map(EscrowTransaction escrowTransaction);
    EscrowTransaction map(EscrowTransactionEntity escrowTransactionEntity);
    EscrowTransaction map(EscrowEvent escrowEvent);
    EscrowTransactionEntity toEntity(EscrowTransaction escrowTransaction);

}
