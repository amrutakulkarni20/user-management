package com.user.usermanagement.domain.service;

import com.user.usermanagement.domain.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void createUSer(UserModel userModel);
}
