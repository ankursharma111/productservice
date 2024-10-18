package com.ankur.bms.productservice.dtos;


import com.ankur.bms.productservice.models.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
public class Role extends BaseModel {

    private String name;

}
