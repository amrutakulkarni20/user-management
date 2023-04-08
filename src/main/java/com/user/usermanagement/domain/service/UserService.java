package com.user.usermanagement.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.user.usermanagement.domain.model.UserModelRequest;
import com.user.usermanagement.domain.model.UserModelResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserModelResponse> createUSer(List<UserModelRequest> userModelRequest) throws JsonProcessingException;

    List<UserModelResponse> getUsers();
}
