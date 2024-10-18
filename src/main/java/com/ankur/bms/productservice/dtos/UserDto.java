package com.ankur.bms.productservice.dtos;

import lombok.*;

import java.util.*;


@Getter
@Setter
public class UserDto {

    private String name;
    private String email;

    private List<Role> roles;
    private Boolean isEmailVerified;


}
