package home.udemy.rest.controller;

import home.udemy.rest.dto.UserDto;
import home.udemy.rest.request.UserDetailsRequestModel;
import home.udemy.rest.response.UserRest;
import home.udemy.rest.service.UserService;
import home.udemy.rest.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public UserRest getUser(){
        return new UserRest();
    }
    @PostMapping
    public UserRest saveUser(UserDetailsRequestModel userDetails){
        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createUser = userService.createUser(userDto);


        BeanUtils.copyProperties(createUser, returnValue);
        return returnValue;
    }

    @PutMapping
    public String updateUser(){
        return "";
    }

    @DeleteMapping
    public String deleteUser(){
        return "";
    }


}
