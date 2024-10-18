package com.ankur.bms.productservice.models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private String title;
    private Double price;
    @ManyToOne(cascade = {CascadeType.ALL})
                              // @JoinColumn(name="category_id ")//this will do exactly same thing to Category which is done
    private Category category;             // to Product, like if a product is deleted then it will
    private String description;            // also delete the category.
    private String imageURL;
    private int numberOfSales;




}
