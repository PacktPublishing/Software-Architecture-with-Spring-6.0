package com.packt.simpletests;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class AuctionBidTest {

    @Test
    void givenHigherBid_whenPlaceBid_thenUpdatesHighestBid() {
        AuctionBid bid = new AuctionBid("alice");

        bid.placeBid(new BigDecimal("100.00"));
        assertThat(bid.getHighestBid()).isEqualByComparingTo(new BigDecimal("100.00"));
    }

    @Test
    void givenLowerOrEqualBid_whenPlaceBid_thenThrowsException() {
        AuctionBid bid = new AuctionBid("bob");
        bid.placeBid(new BigDecimal("150.00"));

        assertThatThrownBy(() -> bid.placeBid(new BigDecimal("100.00")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Bid must be higher than current highest bid");

    }

    @Test
    void bidderName_shouldRemainConstant() {
        AuctionBid bid = new AuctionBid("carol");
        assertThat("carol").isEqualTo(bid.getBidder());
    }

    /** MOCK **/
    @Test
    void mock_shouldReturnStubbedValueAndNotCallRealLogic() {
        AuctionBid mockBid = mock();
        when(mockBid.getHighestBid()).thenReturn(new BigDecimal("999.00"));
        BigDecimal result = mockBid.getHighestBid();
        assertThat(result).isEqualTo(new BigDecimal("999.00"));
    }

    /** SPY **/

    @Test
    void givenStubbedHighestBid_whenPlacingLowerBid_thenThrowsException() {
        AuctionBid realBid = new AuctionBid("Charlie");
        AuctionBid spyBid = spy(realBid);

        // Stub the getHighestBid() to simulate that the highest bid is already 200.00
        doReturn(new BigDecimal("200.00"))
                .when(spyBid)
                .getHighestBid();

        // Now even without calling placeBid(200), getHighestBid() will act as if it were 200
        assertThatThrownBy(() -> spyBid.placeBid(new BigDecimal("150.00")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Bid must be higher than current highest bid");
    }


    @Test
    void givenStubbedHighestBid_whenPlacingHigherBid_thenUpdatesHighestBid() {
        AuctionBid realBid = new AuctionBid("Charlie");
        AuctionBid spyBid = spy(realBid);

        // Stub the getHighestBid() to simulate that the highest bid is already 200.00
        doReturn(new BigDecimal("200.00"))
                .when(spyBid)
                .getHighestBid();

        // Attempt to place a higher bid
        spyBid.placeBid(new BigDecimal("300.00"));

    }

}