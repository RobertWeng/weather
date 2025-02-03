package com.weng.weather.ws;

import com.weng.weather.exception.Catch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@Slf4j
public class GenericServiceClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    // Method to handle all HTTP requests
    private <T, R> ResponseEntity<T> sendRequest(HttpMethod method, String baseUrl, String uri, R body, Class<T> responseType, Map<String, String> queryParam, Object... uriVariables) {
        WebClient webclient = webClientBuilder
                .baseUrl(baseUrl)
                .build();

        WebClient.RequestBodyUriSpec bodySpec = webclient
                .method(method);
        WebClient.RequestHeadersSpec<?> requestSpec;

        requestSpec = bodySpec.uri(uriBuilder -> {
            uriBuilder.path(uri);
            // Add query param supports
            if (queryParam != null) {
                queryParam.forEach(uriBuilder::queryParam);
            }
            return uriBuilder.build(uriVariables);
        });

        // Only methods that can have a body (POST, PUT) should use bodyValue
        if (method == HttpMethod.POST || method == HttpMethod.PUT) {
            requestSpec = bodySpec.bodyValue(body);
        }

        ResponseEntity<T> res = requestSpec
                .retrieve()
                .toEntity(responseType)
                .block();
        return handleResponse(res);
    }

    // Specific methods for each HTTP method (GET, POST, PUT, DELETE)
    // Usually GET contain query param
    public <T> ResponseEntity<T> get(String baseUrl, String uri, Class<T> responseType, Map<String, String> queryParam, Object... uriVariables) {
        return sendRequest(HttpMethod.GET, baseUrl, uri, null, responseType, queryParam, uriVariables);
    }

    public <T, R> ResponseEntity<T> post(String baseUrl, String uri, R body, Class<T> responseType, Object... uriVariables) {
        return sendRequest(HttpMethod.POST, baseUrl, uri, body, responseType, null, uriVariables);
    }

    public <T, R> ResponseEntity<T> put(String baseUrl, String uri, R body, Class<T> responseType, Object... uriVariables) {
        return sendRequest(HttpMethod.PUT, baseUrl, uri, body, responseType, null, uriVariables);
    }

    public ResponseEntity<Void> delete(String baseUrl, String uri, Object... uriVariables) {
        return sendRequest(HttpMethod.DELETE, baseUrl, uri, null, Void.class, null, uriVariables);
    }

    private <T> ResponseEntity<T> handleResponse(ResponseEntity<T> responseEntity) {
        if (responseEntity == null) throw Catch.wsConnectFailed();

        HttpStatusCode statusCode = responseEntity.getStatusCode();
        if (statusCode.is2xxSuccessful()) return responseEntity;
        else {
            log.error("Request failed with status: {} and body: {}", statusCode, responseEntity.getBody());
            throw Catch.wsConnectFailed();
        }
    }
}