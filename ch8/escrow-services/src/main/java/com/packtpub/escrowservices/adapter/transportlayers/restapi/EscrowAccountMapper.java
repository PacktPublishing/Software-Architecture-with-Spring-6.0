package com.packtpub.escrowservices.adapter.transportlayers.restapi;

import com.packtpub.escrowservices.adapter.datasources.escrowAccount.EscrowAccountEntity;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.request.EscrowAccountRequest;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.response.EscrowAccountResponse;
import com.packtpub.escrowservices.internal.entity.EscrowAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EscrowAccountMapper {
    EscrowAccountMapper INSTANCE = Mappers.getMapper(EscrowAccountMapper.class);

    EscrowAccount map(EscrowAccountRequest escrowAccountRequest);
    EscrowAccountResponse map(EscrowAccount escrowAccount);
    EscrowAccount map(EscrowAccountEntity escrowAccountEntity);
    EscrowAccountEntity toDocument(EscrowAccount escrowAccount);
}
