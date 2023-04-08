package com.user.usermanagement.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.user.usermanagement.domain.model.UserModelRequest;
import com.user.usermanagement.domain.model.UserModelResponse;
import com.user.usermanagement.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public List<UserModelResponse> createUser(@Validated @RequestBody List<UserModelRequest> userModelRequest) throws JsonProcessingException {
        return userService.createUSer(userModelRequest);
    }

    @GetMapping("/all")
    public List<UserModelResponse> getUsers(){
        return userService.getUsers();
    }
}
