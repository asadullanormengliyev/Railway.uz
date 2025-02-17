package uz.pdp.railwayuserservice.controller.converter;

import uz.pdp.railwayuserservice.controller.dto.UserCreateRequest;
import uz.pdp.railwayuserservice.controller.dto.UserCreateResponseDto;
import uz.pdp.railwayuserservice.entity.RoleEntity;
import uz.pdp.railwayuserservice.entity.UserEntity;

import java.util.stream.Collectors;

public class UserConverter {

    public static UserCreateResponseDto fromUserEntity(UserEntity userEntity) {
        return UserCreateResponseDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .age(userEntity.getAge())
                .roles(userEntity.getRoles().stream().map(RoleEntity::getRole).collect(Collectors.toList()))
                .build();
    }

    public static UserEntity toUserEntity(UserCreateRequest request) {
        return UserEntity.builder()
                .age(request.getAge())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }
}
