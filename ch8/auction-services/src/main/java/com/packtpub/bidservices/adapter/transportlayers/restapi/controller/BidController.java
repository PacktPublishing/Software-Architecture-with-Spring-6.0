package com.packtpub.bidservices.adapter.transportlayers.restapi.controller;

import com.packtpub.bidservices.adapter.datasources.bid.kafka.BidProducer;
import com.packtpub.bidservices.adapter.transportlayers.restapi.BidMapper;
import com.packtpub.bidservices.adapter.transportlayers.restapi.dto.request.BidRequest;
import com.packtpub.bidservices.adapter.transportlayers.restapi.dto.response.BidResponse;
import com.packtpub.bidservices.internal.entity.Bid;
import com.packtpub.bidservices.internal.usecases.CreateBidUseCase;
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
@RequestMapping("/v1/bids")
public class BidController {

    private final CreateBidUseCase createBidUseCase;

    @Operation(summary = "Create bid", description = "Create a bid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BidResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @PostMapping
    public BidResponse create(@RequestBody BidRequest bidRequest) {

        Bid bid = BidMapper.INSTANCE.map(bidRequest);
        bidRequest.setCreatedAt(bidRequest.getCreatedAt());
        Bid bid1 = createBidUseCase.execute(bid);
        return BidMapper.INSTANCE.map(bid1);

    }

}