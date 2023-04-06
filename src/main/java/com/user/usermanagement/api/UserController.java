package com.user.usermanagement.api;

import com.user.usermanagement.domain.model.UserModel;
import com.user.usermanagement.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    
    public void createUser(@Validated @RequestBody UserModel userModel){
        userService.createUSer(userModel);
    }
}
