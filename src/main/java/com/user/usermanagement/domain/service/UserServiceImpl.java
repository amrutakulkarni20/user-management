package com.user.usermanagement.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.usermanagement.client.AddressApiClient;
import com.user.usermanagement.domain.entity.AddressEntity;
import com.user.usermanagement.domain.entity.UserEntity;
import com.user.usermanagement.domain.model.Address;
import com.user.usermanagement.domain.model.UserModelRequest;
import com.user.usermanagement.domain.model.UserModelResponse;
import com.user.usermanagement.domain.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressApiClient addressApiClient;

    @Override
    public UserModelResponse createUSer(UserModelRequest userModelRequest) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        AddressEntity addressEntity = new AddressEntity();
        UserEntity userEntity = new UserEntity();
        final String zipcode = userModelRequest.getZipcode();
        String address = addressApiClient.getAddressByZipcode(zipcode);
        List<Address> addresses = mapper.readValue(address, new TypeReference<List<Address>>(){});
        Address addressDetail = addresses.get(0);
        modelMapper.map(addressDetail,addressEntity);
        userEntity.setAddress(addressEntity);
        modelMapper.map(userModelRequest,userEntity);
        userRepository.save(userEntity);
        UserModelResponse userResponse = mapUserResponse(userEntity, addressDetail); //TODO : refactor this code to create specific ModelMapper
        return userResponse;
    }

    private UserModelResponse mapUserResponse(UserEntity userEntity, Address addressDetail) {
        UserModelResponse userResponse = new UserModelResponse();

        userResponse.setId(userEntity.getId());
        userResponse.setFirstName(userEntity.getFirstName());
        userResponse.setLastName(userEntity.getLastName());
        userResponse.setMobileNo(userEntity.getMobileNo());
        userResponse.setDateOfBirth(userEntity.getDateOfBirth());
        userResponse.setAddress(addressDetail);
        return userResponse;
    }
}
