package com.ankur.bms.productservice.services;

import com.ankur.bms.productservice.dtos.*;
import com.ankur.bms.productservice.exceptions.*;
import com.ankur.bms.productservice.models.*;
import com.ankur.bms.productservice.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.util.*;

@Primary
@Service("selfproductservice")
public class SelfProductService implements ProductService{

    private final CategoryRepository categoryRepository;
    private ProductRepository productRepository;
   private CategoryRepository CategoryRepository;


    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository CategoryRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.CategoryRepository = CategoryRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {

        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with ID" + id + " not found ");
        }

        Product product = productOptional.get();

        return product;


    }



    @Override
    public Product addNewProduct(Product product) {

    Optional<Category> optionalCategory = categoryRepository.findByName(product.getCategory().getName());

            if(optionalCategory.isEmpty()){
//                Category savedCategory = categoryRepository.save(product.getCategory());
//                product.setCategory(savedCategory);
            }


//here I ahe used @Casecade(cascadeType.All) which will autmatically create a new category in DB
//if a new category is given inside product json, so no need to write code for
//(save new category first --> ssign that new category to our product --> save the product in DB)
//which i am doing above simply use @Cascade
//        Note: using @CascadeType.All will delete the category if the product is deleted if i write
//                @cascade in front of category in Product class. and if i do vice versa then if I delete
//        so better use @cascadeType.save.delete.etc....


    return productRepository.save(product);


//        Category category = product.getCategory();
//
//        if(category.getId() == null){

//            Category savedCategory = categoryRepository.save(category);
//            product.setCategory(savedCategory);
//        }

//        return productRepository.save(product);

    }


    @Override
    public void deleteNewProduct(Long id) {

    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int sizeOfPage, String sortBy, String sortOrder) {

        Sort sort = Sort.by("price").ascending().and(Sort.by("name").descending());


        return productRepository.findAll(PageRequest.of(pageNumber, sizeOfPage, sort));

    }

    @Override
    public Product replaceProduct(Long id, Product product) {

        Optional<Product> productOptional = productRepository.findById(id);


          return productRepository.save(product);

    }

    @Override
    public Product updateProduct(Long id, Product product) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()) throw new RuntimeException();

        Product savedProduct = optionalProduct.get();

        if(product.getTitle()!=null){  //that means we have to replace title of new product with new product title.
            savedProduct.setTitle(product.getTitle());
        }
        if(product.getDescription()!=null){
            savedProduct.setDescription(product.getDescription());
        }
        if(product.getCategory()!=null){
            savedProduct.setCategory(product.getCategory());
        }
        if(product.getPrice()!=null){
            savedProduct.setPrice(product.getPrice());
        }
        if(product.getImageURL()!=null){
            savedProduct.setImageURL(product.getImageURL());
        }

       return productRepository.save(savedProduct);


    }


    @Override
    public boolean deleteProduct(Long id) {


        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()){
            return false;
        }

            productRepository.delete(productOptional.get());
            return true;


    }
}
