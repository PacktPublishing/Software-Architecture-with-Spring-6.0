//package com.packtpub.authenticationservices.contracttest;
//
//import au.com.dius.pact.consumer.*;
//
//import au.com.dius.pact.consumer.model.MockProviderConfig;
//import au.com.dius.pact.core.model.RequestResponsePact;
//import org.apache.http.entity.ContentType;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import static au.com.dius.pact.consumer.ConsumerPactRunnerKt.runConsumerTest;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class PactTest {
//
//    @Test
//    public void testPact() {
//        RequestResponsePact pact = ConsumerPactBuilder
//                .consumer("Some Consumer")
//                .hasPactWith("Some Provider")
//                .uponReceiving("a request to say Hello")
//                .path("/hello")
//                .method("POST")
//                .body("{\"name\": \"harry\"}")
//                .willRespondWith()
//                .status(200)
//                .body("{\"hello\": \"harry\"}")
//                .toPact();
//
//        MockProviderConfig config = MockProviderConfig.createDefault();
//        PactVerificationResult result = runConsumerTest(pact, config, new PactTestRun() {
//            @Override
//            public Object run(@NotNull MockServer mockServer, @Nullable PactTestExecutionContext pactTestExecutionContext) throws Throwable {
//                return null;
//            }
////
////            @Override
////            public void run(@NotNull MockServer mockServer) throws IOException {
////                Map expectedResponse = new HashMap();
////                expectedResponse.put("hello", "harry");
////                assertEquals(expectedResponse, new ConsumerClient(mockServer.getUrl()).post("/hello",
////                        "{\"name\": \"harry\"}", ContentType.APPLICATION_JSON));
////            }
//        });
//
//        if (result instanceof PactVerificationResult.Error) {
//            throw new RuntimeException(((PactVerificationResult.Error)result).getError());
//        }
//
////        assertEquals(PactVerificationResult.Ok, result);
//    }
//
//}