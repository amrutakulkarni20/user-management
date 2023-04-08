package com.user.usermanagement.domain.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.usermanagement.client.AddressApiClient;
import com.user.usermanagement.domain.entity.AddressEntity;
import com.user.usermanagement.domain.entity.UserEntity;
import com.user.usermanagement.domain.model.Address;
import com.user.usermanagement.domain.model.UserModelRequest;
import com.user.usermanagement.domain.model.UserModelResponse;
import com.user.usermanagement.domain.repository.UserMongoRepository;
import com.user.usermanagement.domain.repository.UserRepository;
import com.user.usermanagement.domain.view.UserView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressApiClient addressApiClient;

    @Autowired
    UserMongoRepository userMongoRepository;

    @Override
    public List<UserModelResponse> createUSer(List<UserModelRequest> userModelRequest) throws JsonProcessingException {

        return userModelRequest.stream()
                .filter(userDetail -> checkIfZipCodeIsValid(userDetail))
                .map(userDetail-> {
                    try {
                        return enrichUserDetailsWithAddress(userDetail);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(userDetailEntity -> userRepository.save(userDetailEntity))
                .map(userEntityDetails -> mapUserEntityToUSerResponse(userEntityDetails))
                        .collect(Collectors.toList());

    }

    @Override
    public List<UserModelResponse> getUsers() {
        ObjectMapper mapper = new ObjectMapper();
        List<UserEntity> userEntity = userRepository.findAll();

        return userEntity.stream()
                .map(userEntityDetails -> mapUserEntityToUSerResponse(userEntityDetails))
                .collect(Collectors.toList());
    }

    private UserModelResponse mapUserEntityToUSerResponse(UserEntity userEntityDetails) {
        UserModelResponse userResponse = new UserModelResponse();
        AddressEntity addressEntity = userEntityDetails.getAddress();
        Address address = new Address();
        userResponse.setId(userEntityDetails.getId());
        userResponse.setFirstName(userEntityDetails.getFirstName());
        userResponse.setLastName(userEntityDetails.getLastName());
        userResponse.setMobileNo(userEntityDetails.getMobileNo());
        userResponse.setDateOfBirth(userEntityDetails.getDateOfBirth());
        address.setCity(addressEntity.getCity());
        address.setDistrict(addressEntity.getDistrict());
        address.setState(addressEntity.getState());
        address.setPinCode(addressEntity.getPinCode());
        userResponse.setAddress(address);
        return userResponse;
    }

    private UserEntity enrichUserDetailsWithAddress(UserModelRequest userDetail) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        AddressEntity addressEntity = new AddressEntity();
        UserEntity userEntity = new UserEntity();
        String address = addressApiClient.getAddressByZipcode(userDetail.getZipcode());
        List<Address> addresses = mapper.readValue(address, new TypeReference<List<Address>>(){});
        Address addressDetail = addresses.get(0);
        modelMapper.map(addressDetail,addressEntity);
        modelMapper.map(userDetail,userEntity);
        userEntity.setAddress(addressEntity);
        return userEntity;
    }

    private boolean checkIfZipCodeIsValid(UserModelRequest userModelRequest) {

        UserView userView = userMongoRepository.findStatusByPincode(userModelRequest.getZipcode());
        if (Objects.nonNull(userView) && !userView.getStatus().isEmpty() && userView.getStatus().equalsIgnoreCase("y")) {
            return true;
        }
        return false;
    }
}
