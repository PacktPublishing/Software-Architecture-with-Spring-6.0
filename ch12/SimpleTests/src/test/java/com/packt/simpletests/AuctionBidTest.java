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
        AuctionBid mockBid = mock(AuctionBid.class);
        when(mockBid.getHighestBid()).thenReturn(new BigDecimal("999.00"));
        BigDecimal result = mockBid.getHighestBid();
        assertThat(result).isEqualTo(new BigDecimal("999.00"));
    }

    /** SPY **/
    @Test
    void spy_shouldCallRealMethodUnlessStubbed() {
        AuctionBid realBid = new AuctionBid("alice");
        AuctionBid spyBid = spy(realBid);
        spyBid.placeBid(new BigDecimal("100.00"));
        assertThat(spyBid.getHighestBid()).isEqualByComparingTo("100.00");
    }

}