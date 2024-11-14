//package com.packtpub.authenticationservices.contracttest;
//
//import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
//import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
//import au.com.dius.pact.core.model.RequestResponsePact;
//import au.com.dius.pact.core.model.annotations.Pact;
//import com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class UserContractTests {
//
//    @Pact(consumer = "test_consumer")
//    public RequestResponsePact createPact(PactDslWithProvider builder) {
//        return builder
//                .given("Get roles for user 1")
//                .uponReceiving("A request to get user roles")
//                .path("/v1/users/admin@wxauction.com/roles")
//                .method("GET")
//                .willRespondWith()
//                .status(200)
//                .headers(Map.of("Content-Type", "application/json"))
//                .body(new PactDslJsonBody()
//                        .array("roles")
//                        .stringValue("ADMIN")
//                        .stringValue("USER")
//                        .closeArray())
//                .toPact();
//    }
//
//    @Test
//    public void testGetUserDetails() {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/v1/users/admin@wxauction.com/roles";
//        RoleResponse response = restTemplate.getForObject(url, RoleResponse.class);
//
//        assertEquals(2, response.getRoles().size());
//        assertEquals("ADMIN", response.getRoles().get(0));
//        assertEquals("USER", response.getRoles().get(1));
//    }
//}