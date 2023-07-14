package com.example.success2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BankService {
    private final WebClient webClient;

    public BankService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void fetchDataFromNbpApi() {
        webClient.get()
                .uri("http://api.nbp.pl/")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> {
                    // Process the API response
                });
    }
}
