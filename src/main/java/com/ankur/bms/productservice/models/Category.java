package com.ankur.bms.productservice.models;

import java.util.*;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Category extends BaseModel{

    private String name;

    @OneToMany(mappedBy = "category")//,cascade = {CascadeType.ALL})
    @JsonIgnore
    List<Product> products;

}
