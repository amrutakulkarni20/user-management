package com.user.usermanagement;

import com.user.usermanagement.client.AddressApiClient;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.user.usermanagement"})
public class UserManagementApplication {

	public static void main(String[] args) {
		System.out.println("Hello Welcome to User Management System !!!");
		SpringApplication.run(UserManagementApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
