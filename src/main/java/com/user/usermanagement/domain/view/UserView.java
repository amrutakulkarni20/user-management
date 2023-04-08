package com.user.usermanagement.domain.view;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "user-details")
public class UserView {

    @Id
    private String id;

    @Field(value = "pincode")
    private String pincode;

    @Field(value = "status")
    private String status;
}
