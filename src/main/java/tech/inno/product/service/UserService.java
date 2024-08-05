package tech.inno.product.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tech.inno.product.dto.UserDto;
import tech.inno.product.dto.UserResponseDto;
import tech.inno.product.exception.ServiceException;
import tech.inno.product.entity.User;
import tech.inno.product.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto saveUser(UserDto userDto) {
        User user = save(new User(), userDto);
        return new UserResponseDto(user.getId(), user.getUserName());
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Пользователь id=" + id + " не найден"));
        return new UserResponseDto(user.getId(), user.getUserName());
    }

    public UserResponseDto updateUser(Long id, UserDto userDto) {
        User user = findUserById(id);
        user = save(user, userDto);
        return new UserResponseDto(user.getId(), user.getUserName());
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Пользователь id=" + id + " не найден"));
    }

    public User save(User user, UserDto userDto) {
        user.setUserName(userDto.username());
        return userRepository.save(user);
    }
}
