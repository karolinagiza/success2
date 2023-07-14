package com.example.success2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.awt.*;
@RestController
public class CurrencyController {

    private final WebClient webClient;

    public CurrencyController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/currencies")
    public Mono<List<Currency>> getCurrencies() {
        return webClient.get()
                .uri("http://api.nbp.pl/api/exchangerates/tables/A/")  // Replace with the appropriate NBP API endpoint
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Currency>>() {})
                .map(currencies -> {
                    // Process currencies if necessary
                    return currencies;
                });
    }

    @GetMapping("/convert")
    public Mono<Double> convertCurrency(@RequestParam("amount") double amount,
                                        @RequestParam("sourceCurrency") String sourceCurrency,
                                        @RequestParam("targetCurrency") String targetCurrency) {
        return webClient.get()
                .uri("/convert?amount={amount}&from={sourceCurrency}&to={targetCurrency}", amount, sourceCurrency, targetCurrency)
                .retrieve()
                .bodyToMono(Double.class)
                .map(result -> {
                    // Process the result if necessary
                    return result;
                });
    }

    @RestController
    public class CurrencyController {

        private final WebClient webClient;

        public CurrencyController(WebClient webClient) {
            this.webClient = webClient;
        }

        @GetMapping("/exchange-rates")
        public Mono<List<ExchangeRate>> getExchangeRates() {
            return webClient.get()
                    .uri("/exchange-rates")  // Replace with the appropriate NBP API endpoint
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<ExchangeRate>>() {})
                    .map(exchangeRates -> {
                        // Process exchange rates if necessary
                        return exchangeRates;
                    });
        }
    }
}
