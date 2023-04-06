package com.user.usermanagement.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

    @NotBlank(message = "Street should not be empty")
    private String street;

    @NotBlank(message = "City should not be empty")
    private String city;

    @NotBlank(message = "Country should not be empty")
    private String country;

    @NotBlank(message = "Postcode should not be empty")
    private String postalCode;
}
