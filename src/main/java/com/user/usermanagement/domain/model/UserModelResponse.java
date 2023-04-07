package com.user.usermanagement.domain.model;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserModelResponse {

    private int id;
    @NotBlank(message = "First Name should not be empty")
    private String firstName;

    @NotBlank(message = "Last Name should not be empty")
    private String lastName;

    @NotBlank(message = "Mobile Number should not be empty")
    private String mobileNo;

    @NotBlank(message = "Date of birth should not be empty")
    private String dateOfBirth;

    private Address address;
}
