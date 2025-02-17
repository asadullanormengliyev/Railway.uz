package uz.pdp.bookingservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.pdp.bookingservice.controller.dto.TokenValidationRequestDto;
import uz.pdp.bookingservice.controller.dto.TokenValidationResponseDto;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RestTemplate restTemplate;
    private static final String AUTH_TOKEN_VALIDATION_URL = "http://railway-user-service/api/v1/user/token/validate";

    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackValidateToken")
    public TokenValidationResponseDto validateToken(String token) {
        TokenValidationRequestDto requestBody = new TokenValidationRequestDto(token);
        ResponseEntity<TokenValidationResponseDto> response = restTemplate.exchange(
                AUTH_TOKEN_VALIDATION_URL,
                HttpMethod.POST,
                new HttpEntity<>(requestBody),
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    public TokenValidationResponseDto fallbackValidateToken(String token, Throwable throwable) {
        return new TokenValidationResponseDto("fallback-user", Set.of("ROLE_USER"));
    }
}
