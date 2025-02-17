package uz.pdp.railwayuserservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.railwayuserservice.controller.dto.JwtResponseDto;
import uz.pdp.railwayuserservice.controller.dto.RefreshTokenDto;
import uz.pdp.railwayuserservice.controller.dto.TokenValidationRequestDto;
import uz.pdp.railwayuserservice.controller.dto.TokenValidationResponseDto;
import uz.pdp.railwayuserservice.service.TokenService;

@RestController
@RequestMapping("/api/v1/user/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    public JwtResponseDto refreshToken(
            @RequestBody RefreshTokenDto refreshTokenDto
    ) throws JsonProcessingException {
        String accessToken = tokenService.generateAccessToken(refreshTokenDto);
        return new JwtResponseDto("Bearer " + accessToken);
    }

    @PostMapping("/validate")
    public TokenValidationResponseDto validateToken(
            @RequestBody TokenValidationRequestDto request
    ) {
        return tokenService.validateAccessToken(request.getToken());
    }

}
