package com.ankur.bms.productservice.services;

import com.ankur.bms.productservice.dtos.*;
import com.ankur.bms.productservice.exceptions.*;
import com.ankur.bms.productservice.models.*;
import org.springframework.context.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

public interface ProductService {

    Product getSingleProduct(Long id) throws ProductNotFoundException;


    void deleteNewProduct(Long id);

    Page<Product> getAllProducts(int pageNumber, int sizeOfPage, String sortBy, String sortOrder);

    Product replaceProduct(Long id, Product product);

    Product updateProduct(Long id, Product product);

    Product addNewProduct(Product product);

    boolean deleteProduct(Long id);

}
