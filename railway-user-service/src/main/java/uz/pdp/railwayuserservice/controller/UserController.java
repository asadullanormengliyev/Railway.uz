package uz.pdp.railwayuserservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.pdp.railwayuserservice.controller.converter.UserConverter;
import uz.pdp.railwayuserservice.controller.dto.JwtResponseDto;
import uz.pdp.railwayuserservice.controller.dto.UserCreateRequest;
import uz.pdp.railwayuserservice.controller.dto.UserCreateResponseDto;
import uz.pdp.railwayuserservice.entity.Ticket;
import uz.pdp.railwayuserservice.entity.UserEntity;
import uz.pdp.railwayuserservice.service.TicketService;
import uz.pdp.railwayuserservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TicketService ticketService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public UserCreateResponseDto saveUser(@RequestBody UserCreateRequest request) {
        UserEntity userEntity = userService.save(UserConverter.toUserEntity(request));
        return UserConverter.fromUserEntity(userEntity);
    }

    @PostMapping("/login")
    public JwtResponseDto login(@RequestBody UserCreateRequest request) throws JsonProcessingException {
        return userService.login(request.getUsername(), request.getPassword());
    }

    @GetMapping("/{id}/ticket")
    public List<Ticket> getTicketsByUserId(@PathVariable int id) {
        return ticketService.getTicketsByUserId(id);
    }

}
