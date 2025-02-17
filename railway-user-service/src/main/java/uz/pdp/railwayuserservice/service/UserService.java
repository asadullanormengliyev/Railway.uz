package uz.pdp.railwayuserservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.railwayuserservice.controller.dto.JwtResponseDto;
import uz.pdp.railwayuserservice.entity.RoleEntity;
import uz.pdp.railwayuserservice.entity.UserEntity;
import uz.pdp.railwayuserservice.entity.enums.UserRole;
import uz.pdp.railwayuserservice.repository.RoleRepository;
import uz.pdp.railwayuserservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;


    public UserEntity save(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(List.of(getUserRole()));
        return userRepository.save(userEntity);
    }

    public JwtResponseDto login(String username, String password) throws JsonProcessingException {
        Optional<UserEntity> optionalUserEntity =
                userRepository.findByUsername(username);
        if (optionalUserEntity.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        UserEntity userEntity = optionalUserEntity.get();
        boolean matches = passwordEncoder.matches(password, userEntity.getPassword());
        if (!matches) {
            throw new UsernameNotFoundException(username);
        }

        String accessToken = jwtService.generateAccessToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);
        return new JwtResponseDto("Bearer " + accessToken, refreshToken);
    }

    private RoleEntity getUserRole() {
        return roleRepository.findByRole(UserRole.USER);
    }
}
