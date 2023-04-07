package com.user.usermanagement.client;

import com.user.usermanagement.domain.model.Address;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="addressApiClient", url = "https://www.postpincode.in")
public interface AddressApiClient {

    @GetMapping(value = "/api/getCityName.php?pincode={pincode}", produces = "text/html")
    String getAddressByZipcode(@PathVariable("pincode") String pincode);
}
