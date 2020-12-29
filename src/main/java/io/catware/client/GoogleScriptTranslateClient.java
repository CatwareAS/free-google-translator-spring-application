package io.catware.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.annotation.PostConstruct;

@Component
public class GoogleScriptTranslateClient {

    @Value("${google-script-api-url}")
    private String googleScripApiUrl;

    private WebClient googleScriptClient;

    @PostConstruct
    public void postConstruct() {
        googleScriptClient = WebClient.builder()
                .baseUrl(googleScripApiUrl)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create().followRedirect(true)))
                .build();
    }

    public Mono<String> translateText(String text, String targetLanguage) {
        return getTranslatedText(text, "", targetLanguage);
    }

    public Mono<String> translateText(String text, String sourceLanguage, String targetLanguage) {
        return getTranslatedText(text, sourceLanguage, targetLanguage);
    }

    private Mono<String> getTranslatedText(String text, String sourceLanguage, String targetLanguage) {
        return googleScriptClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", text)
                        .queryParam("source", sourceLanguage)
                        .queryParam("target", targetLanguage)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

}
