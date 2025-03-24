package com.packtpub.escrowservices.adapter.datasources.escrowTransaction.kafka;

import com.packtpub.escrowservices.adapter.transportlayers.restapi.EscrowTransactionMapper;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.event.BidEvent;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.event.EscrowEvent;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.request.EscrowTransactionRequest;
import com.packtpub.escrowservices.internal.entity.EscrowTransaction;
import com.packtpub.escrowservices.internal.enums.BidStatus;
import com.packtpub.escrowservices.internal.enums.EscrowStatus;
import com.packtpub.escrowservices.internal.usecases.CreateEscrowTransationUseCase;
import com.packtpub.escrowservices.internal.usecases.GetUserEscrowBalanceUseCase;
import com.packtpub.escrowservices.internal.usecases.GetUserEscrowReservationsUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Component
public class BidConsumer {

    private final EscrowProducer escrowProducer;

    private final GetUserEscrowBalanceUseCase getUserEscrowBalanceUseCase;
    private final GetUserEscrowReservationsUseCase getUserEscrowReservationsUseCase;
    private final CreateEscrowTransationUseCase createEscrowTransationUseCase;

    public BidConsumer(EscrowProducer escrowProducer, GetUserEscrowBalanceUseCase getUserEscrowBalanceUseCase, GetUserEscrowReservationsUseCase getUserEscrowReservationsUseCase, CreateEscrowTransationUseCase createEscrowTransationUseCase) {
        this.escrowProducer = escrowProducer;
        this.getUserEscrowBalanceUseCase = getUserEscrowBalanceUseCase;
        this.getUserEscrowReservationsUseCase = getUserEscrowReservationsUseCase;
        this.createEscrowTransationUseCase = createEscrowTransationUseCase;
    }

    @KafkaListener(topics = "bids-topic", groupId = "escrow-group")
    public void bidConsumer(BidEvent bidEvent) {
        log.info("Bid {} processing from bids topic!", bidEvent.getId());

        // Process and check if the user has founds
        BigDecimal balance = getUserEscrowBalanceUseCase.execute(bidEvent.getUserId());
        BigDecimal totalFundReserved = getUserEscrowReservationsUseCase.execute(bidEvent.getUserId());

        EscrowTransaction escrowTransaction = new EscrowTransaction(
                null,
                bidEvent.getAuctionId(),
                bidEvent.getId(),
                bidEvent.getUserId(),
                bidEvent.getAmount(),
                null,
                bidEvent.getCreatedAt());

        if(balance.compareTo(totalFundReserved.add(bidEvent.getAmount())) >= 0){
            bidEvent.setStatus(BidStatus.ACCEPTED.name());
            escrowTransaction.setStatus(EscrowStatus.RESERVED.name());
        } else{
            bidEvent.setStatus(BidStatus.REJECTED.name());
            escrowTransaction.setStatus(EscrowStatus.REJECTED.name());
        }

        createEscrowTransationUseCase.execute(escrowTransaction);

        escrowProducer.placeEscrow(bidEvent);

        log.info("Bid {} processed from bids topic!", bidEvent.getId());

    }
}