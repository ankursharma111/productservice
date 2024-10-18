package com.ankur.bms.productservice.commons;

import com.ankur.bms.productservice.dtos.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;


@Service
public class AuthenticationCommons {

    RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token){

        ResponseEntity<UserDto> userDtoReponse = restTemplate.postForEntity(
                "https://localhost:8181/users/validate/" + token,
                null,
                UserDto.class
        );

        if(userDtoReponse.getBody()==null){
            return null;
        }

        return userDtoReponse.getBody();





    }
}
