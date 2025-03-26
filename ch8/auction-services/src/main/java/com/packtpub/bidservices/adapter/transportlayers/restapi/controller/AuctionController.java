package com.packtpub.bidservices.adapter.transportlayers.restapi.controller;

import com.packtpub.bidservices.adapter.transportlayers.restapi.AuctionMapper;
import com.packtpub.bidservices.adapter.transportlayers.restapi.dto.request.AuctionRequest;
import com.packtpub.bidservices.adapter.transportlayers.restapi.dto.response.AuctionResponse;
import com.packtpub.bidservices.internal.entity.Auction;
import com.packtpub.bidservices.internal.usecases.CreateAuctionUseCase;
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
@RequestMapping("/v1/auctions")
public class AuctionController {

    private final CreateAuctionUseCase createAuctionUseCase;

    @Operation(summary = "Create auction", description = "Create an auction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuctionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public AuctionResponse create(@RequestBody AuctionRequest auctionRequest) {

        Auction auction = AuctionMapper.INSTANCE.map(auctionRequest);
        auction.setCreatedAt(auctionRequest.getCreatedAt());
        Auction auction1 = createAuctionUseCase.execute(auction);
        return AuctionMapper.INSTANCE.map(auction1);

    }

}