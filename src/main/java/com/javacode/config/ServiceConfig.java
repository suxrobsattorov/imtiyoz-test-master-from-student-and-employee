package com.javacode.config;

import com.javacode.constants.TokenObject;
import com.javacode.dto.AuthDTO;
import com.javacode.dto.TokenDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Configuration
public class ServiceConfig {

    private static final Log log = LogFactory.getLog(ServiceConfig.class);

    @Value("${app.token-url}")
    private static String authorizeUrl;

    @Value("${app.username}")
    private static String username;

    @Value("${app.password}")
    private static String password;



    @Bean(name = "tokenWebClient")
    WebClient tokenWebClient() {
        return WebClient.builder()
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .clientConnector(connector())
                .build();
    }


    @Bean(name = "webClient")
    WebClient webClient(@Qualifier("tokenWebClient") WebClient tokenWebClient) {
        return WebClient.builder()
                .clientConnector(connector())
                .filter(renewTokenFilter(tokenWebClient))
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024))
                        .build())
                .build();
    }

    private ClientHttpConnector connector() {
        return new ReactorClientHttpConnector(HttpClient.newConnection());
    }

    public ExchangeFilterFunction renewTokenFilter(@Qualifier("tokenWebClient") WebClient tokenWebClient) {
        return (request, next) -> next.exchange(request).flatMap(response -> {
            log.info(String.format("response.statusCode().value(): %d", response.statusCode().value()));
            if (response.statusCode().value() == HttpStatus.UNAUTHORIZED.value()) {
                return response.releaseBody()
                        .then(renewToken(tokenWebClient))
                        .flatMap(token -> {
                            ClientRequest newRequest = ClientRequest.from(request)
                                    .headers(headers -> headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + TokenObject.token)).build();
                            return next.exchange(newRequest);
                        });

            } else {
                return Mono.just(response);
            }
        });
    }

    private Mono<String> renewToken(@Qualifier("tokenWebClient") WebClient tokenWebClient) {

        AuthDTO authDTO = new AuthDTO("suxrob", "suxrob11");

        return tokenWebClient.post()
                .uri("http://127.0.0.1:8080/api/v1/student/login")
                .body(BodyInserters.fromValue(authDTO))
//                .body(BodyInserters.fromFormData("username", "suxrob")
//                        .with("password", "suxrob11"))
                .retrieve()
                .bodyToMono(TokenDTO.class).map(tokenDTO -> {
                            TokenObject.token = tokenDTO.getAccess_token();
                            return tokenDTO.getAccess_token();
                        }
                );
    }

}
