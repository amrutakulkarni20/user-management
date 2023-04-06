package com.user.usermanagement.domain.service;

import com.user.usermanagement.domain.entity.UserEntity;
import com.user.usermanagement.domain.model.UserModel;
import com.user.usermanagement.domain.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createUSer(UserModel userModel) {
        String zipcode = userModel.getZipcode();


        UserEntity userEntity = new UserEntity();
        modelMapper.map(userModel,userEntity);
        userRepository.save(userEntity);
        userRepository.findAll();
    }
}
