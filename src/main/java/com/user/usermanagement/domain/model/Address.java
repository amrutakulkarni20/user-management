package com.user.usermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

    @JsonProperty("Pincode")
    private String pinCode;

    @JsonProperty("City")
    private String city;

    @JsonProperty("District")
    private String district;

    @JsonProperty("State")
    private String state;
}
