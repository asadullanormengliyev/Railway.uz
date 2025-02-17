package uz.pdp.railwayuserservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.railwayuserservice.controller.dto.RefreshTokenDto;
import uz.pdp.railwayuserservice.controller.dto.TokenValidationResponseDto;
import uz.pdp.railwayuserservice.entity.UserEntity;
import uz.pdp.railwayuserservice.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public String generateAccessToken(RefreshTokenDto refreshTokenDto) throws JsonProcessingException {
        String refreshToken = refreshTokenDto.getRefreshToken();
        jwtService.validateRefreshToken(refreshToken);
        Claims claims = jwtService.refreshTokenClaims(refreshToken);
        String username = claims.getSubject();
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User not found");
        }
        return jwtService.generateAccessToken(optionalUser.get());
    }

    public TokenValidationResponseDto validateAccessToken(String accessToken) {
        try {
            jwtService.validateAccessToken(accessToken);
            Claims claims = jwtService.accessTokenClaims(accessToken);
            String username = claims.getSubject();
            List<Map<String, String>> roles = claims.get("roles", List.class);


            return TokenValidationResponseDto.builder()
                    .username(username)
                    .authorities(roles.stream().map(role -> role.get("authority")).collect(Collectors.toSet()))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
