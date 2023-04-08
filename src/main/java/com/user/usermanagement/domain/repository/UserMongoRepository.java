package com.user.usermanagement.domain.repository;

import com.user.usermanagement.domain.view.UserView;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepository extends MongoRepository <UserView, String>{

    UserView findStatusByPincode(String pincode);
}
