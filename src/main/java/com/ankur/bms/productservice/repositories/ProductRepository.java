package com.ankur.bms.productservice.repositories;


import com.ankur.bms.productservice.models.*;
import com.ankur.bms.productservice.projections.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;

import java.util.*;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findTopByTitleContaining(String word);

    List<Product> findByCategory_Id(Long categoryId); //use class_attribute ->this when we want to find
                                                     // something based on attribute of a class.
    Optional<Product> findById(Long id);

    Product save(Product product);

    Page<Product> findAll(Pageable pageable);




    @Query("select p.id as id, p.title as title from Product p where id= :id")    //HQL Query with parameter id
    List<ProductwithIdAndTitle> somethingsomething(@Param("id") Long id);

    @Query(value = "select * from products p where p.id= :id ",nativeQuery = true)   //NativeSQL Query
    List<Product> somethingsomethingElse(@Param("id") Long id);


    @Query(value = "select p.id as id,p.title as title from Product p where id= :id",nativeQuery = true)
    List<ProductwithIdAndTitle> somethingsomething2(@Param("id") Long id);








}
