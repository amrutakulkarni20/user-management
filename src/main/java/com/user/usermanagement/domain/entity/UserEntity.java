package com.user.usermanagement.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "mobileNo")
    private String mobileNo;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "address")
    @Embedded
    private AddressEntity address;
}
