package com.ankur.bms.productservice.controllers;


import com.ankur.bms.productservice.commons.*;
import com.ankur.bms.productservice.dtos.*;
import com.ankur.bms.productservice.exceptions.*;
import com.ankur.bms.productservice.models.*;
import com.ankur.bms.productservice.services.*;
import org.hibernate.query.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    RestTemplate restTemplate;

    AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier("selfproductservice")ProductService productService,
                             RestTemplate restTemplate,
                             AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.restTemplate = restTemplate;
        this.authenticationCommons = authenticationCommons;

    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {


            return new ResponseEntity<>(
                    productService.getSingleProduct(id),
                    HttpStatus.OK
            );
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product) throws ProductNotFoundException {

         return productService.addNewProduct(product);
      //  return productService.getSingleProduct(product.getId());

    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){ //this product parameter in @RequestBody only have those values which we have to replace...basically its new product which is to be replaced by old product with given id parameter.

       return productService.updateProduct(id,product);

    }


    @DeleteMapping("{id}")
    public boolean deleteProduct(@PathVariable("id") Long id){


        return productService.deleteProduct(id);

    }

    @GetMapping()
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam("pagenumber") int PageNumber,
                                                        @RequestParam("pagesize") int sizeOfPage,
                                                        @RequestParam("sortBy") String sortBy,
                                                        @RequestParam("sortOrder") String sortOrder){//@RequestHeader("Authorization") String token){


        //from line number 76 to 94 is the code for validating token and checking if the user is admin or not.
        //if the user is not admin then it will return unauthorized.
        //if the token is not valid then it will return forbidden.
        //if the token is valid and user is admin then it will return all the products.
        //if the token is valid and user is not admin then it will return unauthorized.
        //if the token is not present then it will return forbidden.
        //For getAllProducts service basically I have created my own OAuth Mechanism which is not scalable
        //as I have to write it again and again for every service so I will use OAuth2 which is
        //Scalable and easy to use.

//        UserDto userDto = authenticationCommons.validateToken(token);
//
//       if(userDto==null) {
//           return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//       }
//
//           boolean isAdmin = false;
//
//           for(Role role : userDto.getRoles()){
//
//               if(role.getName().equals("ADMIN")){
//                   isAdmin = true;
//                   break;
//               }
//           }
//
//           if(!isAdmin){
//               return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//           }

          // return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);


        Page<Product> products = productService.getAllProducts(PageNumber, sizeOfPage);

//        List<Product> finalProducts = new ArrayList<>();
//
//                for(Product p : products){
//            p.setTitle("Hello" + p.getTitle());
//            finalProducts.add(p);
//        }

       ResponseEntity<Page<Product>> response =  new ResponseEntity<>(
               products, HttpStatus.OK

       );

        return response;

    }
    @PostMapping({"id"})
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){

        return productService.replaceProduct(id,product);

    }

//    @GetMapping()
//    public List<Product> getAllProducts(){
//
//        return productService.getAllProducts();
//    }
}
