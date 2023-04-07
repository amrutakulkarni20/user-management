package com.user.usermanagement.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.user.usermanagement.domain.model.UserModelRequest;
import com.user.usermanagement.domain.model.UserModelResponse;
import com.user.usermanagement.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserModelResponse createUser(@Validated @RequestBody UserModelRequest userModelRequest) throws JsonProcessingException {
        return userService.createUSer(userModelRequest);
    }
}
