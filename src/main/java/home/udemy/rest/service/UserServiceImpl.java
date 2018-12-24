package home.udemy.rest.service;

import home.udemy.rest.shared.dto.UserDto;
import home.udemy.rest.io.entity.UserEntity;
import home.udemy.rest.io.repository.UserRepository;
import home.udemy.rest.shared.Utils;
import home.udemy.rest.ui.model.response.UserRest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Utils utils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new RuntimeException("User not found");
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;


    }

    @Override
    public UserDto getUserByUserId(String id) {
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) throw new RuntimeException("User not found");
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = userRepository.findByEmail(userDto.getEmail());

        if (userEntity == null) {
            throw new UsernameNotFoundException("Record not found");
        }
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();

        BeanUtils.copyProperties(storedUserDetails, returnValue);
        return returnValue;

    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, limit);
        Page<UserEntity> usersPage = userRepository.findAll(pageable);
        List<UserEntity> users = usersPage.getContent();

        users.forEach(userDto -> {
            UserDto userRest = new UserDto();
            BeanUtils.copyProperties(userDto, userRest);
            returnValue.add(userRest);
        });
        return returnValue;
    }

    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UsernameNotFoundException("Record already exists");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        userEntity.setEncryptedPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setUserId(utils.generatedUserId());
//        userEntity.setEmailVerificationStatus(false);
        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();

        BeanUtils.copyProperties(storedUserDetails, returnValue);
        return returnValue;
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByEmail(userId);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Record not found");
        }
        userRepository.delete(userEntity);
    }
}
