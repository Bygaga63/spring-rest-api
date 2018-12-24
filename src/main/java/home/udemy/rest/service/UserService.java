package home.udemy.rest.service;

import home.udemy.rest.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUser(String email);
    UserDto getUserByUserId(String id);
    void deleteUser(String userId);
    UserDto updateUser(UserDto userDto);

    List<UserDto> getUsers(int page, int limit);
}
