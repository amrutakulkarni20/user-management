package com.user.usermanagement.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserModel {

    private int id;
    @NotBlank(message = "First Name should not be empty")
    private String firstName;

    @NotBlank(message = "Last Name should not be empty")
    private String lastName;

    @NotBlank(message = "Mobile Number should not be empty")
    private String mobileNo;

    @NotBlank(message = "Date of birth should not be empty")
    private String dateOfBirth;

    @NotBlank(message = "Zipcode should not be empty")
    private String zipcode;
}
