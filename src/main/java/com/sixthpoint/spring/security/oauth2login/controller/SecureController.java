package com.sixthpoint.spring.security.oauth2login.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;

@RestController
public class SecureController {

    @ApiOperation(
            value = "Get the current logged-in user",
            notes = "This example only returns the user logged in from Google")
    @GetMapping("/")
    public HashMap<String, Object> index(@ApiIgnore @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                                         @ApiIgnore @AuthenticationPrincipal OAuth2User oauth2User) {
        HashMap<String, Object> results = new HashMap<>();
        results.put("username", oauth2User.getName());
        results.put("attributes", oauth2User.getAttributes());
        results.put("authorities", oauth2User.getAuthorities());
        results.put("clientScopes", authorizedClient.getClientRegistration().getScopes());
        results.put("clientName", authorizedClient.getClientRegistration().getClientName());
        return results;
    }
}