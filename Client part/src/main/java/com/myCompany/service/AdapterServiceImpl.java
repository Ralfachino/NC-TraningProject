package com.myCompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Service
public class AdapterServiceImpl implements AdapterService {

    @Autowired
    @Qualifier("webClient")
    private WebClient webClient;

    public String getStartMenu() {
        return webClient
                .get()
                .uri(URI.create("localhost:8081/index"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public Boolean getCardMenu(String idCard, String pin) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("localhost:8080/anc")
                        .queryParam("idCard", idCard)
                        .queryParam("pin", pin)
                        .build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public Boolean checkCardBalance(int money) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("localhost:8080/check-balance")
                        .queryParam("money", money)
                        .build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public Integer getCardBalance() {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("localhost:8080/get-balance")
                        .build())
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    public Boolean withdrawMoney(int removeMoney) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("localhost:8080/withdraw-money")
                        .queryParam("removeMoney", removeMoney)
                        .build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public Boolean depositMoney(int addMoney) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("localhost:8080/deposit-money")
                        .queryParam("addMoney", addMoney)
                        .build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
