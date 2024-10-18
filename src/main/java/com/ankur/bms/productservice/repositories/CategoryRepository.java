package com.ankur.bms.productservice.repositories;

import com.ankur.bms.productservice.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;


@Repository
public interface CategoryRepository  extends JpaRepository<Category,Long> {



    Category save(Category category);

    Optional<Category> findByName(Product product);

    Optional<Category> findByName(String name);



}
