package tech.inno.controller;

import org.springframework.web.bind.annotation.*;
import tech.inno.dto.UserDto;
import tech.inno.service.UserService;

import java.sql.SQLException;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public UserDto getProductByUserId(@RequestParam(name = "userid") Long userId) {
        try {
            return userService.fetchUserById(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
