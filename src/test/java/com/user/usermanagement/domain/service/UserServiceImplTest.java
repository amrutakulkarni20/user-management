package com.user.usermanagement.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.user.usermanagement.client.AddressApiClient;
import com.user.usermanagement.domain.entity.AddressEntity;
import com.user.usermanagement.domain.entity.UserEntity;
import com.user.usermanagement.domain.model.UserModelRequest;
import com.user.usermanagement.domain.model.UserModelResponse;
import com.user.usermanagement.domain.repository.UserMongoRepository;
import com.user.usermanagement.domain.repository.UserRepository;
import com.user.usermanagement.domain.view.UserView;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMongoRepository userMongoRepository;

    @Mock
    private AddressApiClient addressApiClient;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Spy
    private ModelMapper modelMapper;

    @Test
    public void getsListOfUsersSuccessfully(){

        List<UserEntity> userEntityList = getListOfUSerEntities();
        when(userRepository.findAll()).thenReturn(userEntityList);
        List<UserModelResponse> userModelResponseList = userServiceImpl.getUsers();
        Assert.assertNotNull(userModelResponseList);
    }

    private List<UserEntity> getListOfUSerEntities() {

        UserEntity userEntity = new UserEntity(1, "testFirstName", "testLastName", "123456789", "15-10-1989", new AddressEntity("411234","Pune", "Pune", "Maharashtra"));
        UserEntity userEntity1 = new UserEntity(1, "testFirstName1", "testLastName1", "1234567891", "15-10-1989", new AddressEntity("4112341","Pune", "Pu1ne", "Mahar1ashtra"));
        List<UserEntity> userEntityList = new ArrayList<>();
        userEntityList.add(userEntity);
        userEntityList.add(userEntity1);
        return userEntityList;
    }

    @Test
    public void returnsEmptyUserModelResponseListWhenNullUserEntityListIsReceived(){

        when(userRepository.findAll()).thenReturn(null);
        List<UserModelResponse> userModelResponseList = userServiceImpl.getUsers();
        Assert.assertTrue(userModelResponseList.isEmpty());
    }

    @Test
    public void verifyZipCodeIsValidWhenUserDetailReceived() throws JsonProcessingException {
        List<UserModelRequest> userModelRequestList = getListOfUserRequest();
        String zipcode = "402201";
        String address = "[{\"Pincode\":\"402201\",\"City\":\"Alibag\",\"District\":\"Raigarh(MH)\",\"State\":\"Maharashtra\"}]";
        UserView userView = new UserView("123456","402201","y");
        when(userMongoRepository.findStatusByPincode(zipcode)).thenReturn(userView);
        when(addressApiClient.getAddressByZipcode(zipcode)).thenReturn(address);
        when(userRepository.save(any())).thenReturn(createUserEntity());
        List<UserModelResponse> userModelResponseList = userServiceImpl.createUSer(userModelRequestList);
        ArgumentCaptor<UserEntity> userEntityArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository, times(1)).save(userEntityArgumentCaptor.capture());
        UserEntity userEntity = userEntityArgumentCaptor.getValue();
        assertNotNull(userEntity);
        Assert.assertTrue(!userModelResponseList.isEmpty());
        Assert.assertEquals(userModelRequestList.get(0).getZipcode(),"402201");
    }

    private UserEntity createUserEntity(){
        UserEntity userEntity = new UserEntity(1,"testFirstName", "testLastName", "123456789", "15-10-1989",new AddressEntity("402201","Alibag","Raigarh","Maharashtra"));
        return userEntity;
    }
    private List<UserModelRequest>  getListOfUserRequest() {
        UserModelRequest userModelRequest = new UserModelRequest(1, "testFirstName", "testLastName", "123456789", "15-10-1989","402201");
        UserModelRequest userModelRequest1 = new UserModelRequest(1, "testFirstName", "testLastName", "123456789", "15-10-1989","422201");
        List<UserModelRequest> userModelRequestList = new ArrayList<>();
        userModelRequestList.add(userModelRequest);
        userModelRequestList.add(userModelRequest1);
        return userModelRequestList;
    }

}
