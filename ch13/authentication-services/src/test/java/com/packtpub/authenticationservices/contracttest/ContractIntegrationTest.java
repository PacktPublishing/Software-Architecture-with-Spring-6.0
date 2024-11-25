package com.packtpub.authenticationservices.contracttest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureStubRunner(ids = {"com.packtpub.userservices.adapter.transportlayers.restapi.controller.contrattesting:producer:+:stubs:8100"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
//public class ContractIntegrationTest {
//
//    @Test
//    public void get_roles() {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<JsonObject> responseEntity = restTemplate.getForEntity("http://localhost:8081/v1/users/admin@wxauction.com/roles", JsonObject.class);
//        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
//
//    }

@ExtendWith(SpringExtension .class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "com.packtpub.userservices:user-services:0.0.1-SNAPSHOT:stubs:8100"
)
public class ContractIntegrationTest {

//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;

//    @Test
//    void should_get_hello_string_correctly() {
//        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/hello/1000", String.class))
//                .isEqualTo("Hello John");
//    }

//    @Test
//    public void get_roles() {
//        TestRestTemplate restTemplate = new TestRestTemplate();
//
//        // Set up headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("traceparent", "ABC"); // Add the required traceparent header
//
//        // Create an entity with headers
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        // Make the GET request with headers
//        ResponseEntity<RoleResponse> responseEntity = restTemplate.exchange(
//                "http://localhost:" + port + "/v1/users/admin@wxauction.com/roles",
//                HttpMethod.GET,
//                entity,
//                RoleResponse.class
//        );
//
//        // Assert the response status code
//        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
//    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(
                        get("/v1/users/admin@wxauction.com/roles").header("traceparent", "ABC"))
                .andDo(print())
                .andExpect(status().isOk());
               // .andExpect(content().string(containsString("Enjoy your new Test Hat 1")));
    }

}
