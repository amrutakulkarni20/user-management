package com.user.usermanagement.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.user.usermanagement.domain.model.UserModelRequest;
import com.user.usermanagement.domain.model.UserModelResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserModelResponse createUSer(UserModelRequest userModelRequest) throws JsonProcessingException;
}
