package com.user.usermanagement.domain.service;

import com.user.usermanagement.domain.model.UserModel;
import com.user.usermanagement.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    //private ModelMapper modelMapper;

    @Override
    public void createUSer(UserModel userModel) {

    }
}
