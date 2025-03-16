package com.packtpub.clienapplication.controller;

import com.packtpub.clienapplication.ProductResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
public class ProductController {
  public RestClient webClient;

  public ProductController(RestClient.Builder builder) {
    this.webClient = builder
        .build();
  }

  @GetMapping("request-access-token")
  public String requestAccessToken(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient oAuth2AuthorizedClient,
      @AuthenticationPrincipal OidcUser oidcUser) {
    return """
        <p> access token: %s
        <p> refresh token: %s
        <p> id token: %s
        <p> Claims: %s
        """.formatted(oAuth2AuthorizedClient.getAccessToken().getTokenValue(),
            oAuth2AuthorizedClient.getRefreshToken().getTokenValue(),
            oidcUser.getIdToken().getTokenValue(),
            oidcUser.getClaims());
  }

  @GetMapping("products")
  public List<ProductResponse> getProducts(
          @RegisteredOAuth2AuthorizedClient("client-server-oidc") OAuth2AuthorizedClient client) {
    return webClient
            .get()
            .uri("http://127.0.0.1:8082/v1/products")
            .header("Authorization", "Bearer %s".formatted(client.getAccessToken().getTokenValue()))
            .retrieve()
            .body(new ParameterizedTypeReference<>() {});
  }
}
