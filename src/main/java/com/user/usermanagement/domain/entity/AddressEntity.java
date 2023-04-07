package com.user.usermanagement.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class AddressEntity {

    @Column(name = "pinCode")
    private String pinCode;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "state")
    private String state;
}
