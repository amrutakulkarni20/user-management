package com.user.usermanagement.domain.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class UserModel {

    private int id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String mobileNo;
    @NonNull
    private Date dateOfBirth;
    @NonNull
    private Address address;
}
