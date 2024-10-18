package com.ankur.bms.productservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.*;
import java.util.*;


@Getter
@Setter
@MappedSuperclass
public class BaseModel implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private boolean isDeleted;

}
