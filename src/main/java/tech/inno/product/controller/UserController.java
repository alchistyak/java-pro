package tech.inno.product.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.inno.product.dto.UserDto;
import tech.inno.product.dto.UserResponseDto;
import tech.inno.product.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public UserResponseDto save(@Valid @RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable(name = "id") Long id, @Valid @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }
}
