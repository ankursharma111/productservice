package com.ankur.bms.productservice.services;

import com.ankur.bms.productservice.dtos.*;
import com.ankur.bms.productservice.exceptions.*;
import com.ankur.bms.productservice.models.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.redis.core.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import java.util.*;


@Service("fakeproductservice")
public class FakeStoreProductService implements ProductService{

        private RestTemplate restTemplate;
        private RedisTemplate<String,Object> redisTemplate;

        //private FakeStoreProductDto fakeStoreProductDto;

        @Autowired
        public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String,Object> redisTemplate) {
            this.restTemplate = restTemplate;
            this.redisTemplate = redisTemplate;

        }

        public Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto) {



            Product product = new Product();
            product.setId(fakeStoreProductDto.getId());
            product.setTitle(fakeStoreProductDto.getTitle());
            product.setDescription(fakeStoreProductDto.getDescription());
            product.setPrice(fakeStoreProductDto.getPrice());
            product.setCategory(new Category());
            product.getCategory().setName(fakeStoreProductDto.getCategory());
            product.getCategory().setId(fakeStoreProductDto.getId());
            product.setImageURL(fakeStoreProductDto.getImage());

            return product;
        }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {


            Product p = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCTS_" + id);

            if(p!=null){
                return p;
            }


           FakeStoreProductDto productDto = restTemplate.getForObject(
                    "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class

            );

           if(productDto == null){
               throw new ProductNotFoundException(
                       "Product with " + id + " doesn't exist."
               );
           }

           Product p1 = convertFakeStoreProductToProduct(productDto);

           redisTemplate.opsForHash().put("PRODUCTS", "PRODUCTS_" + id + "", p1);

           return p1;

    }



    @Override
    public void deleteNewProduct(Long id) {


          restTemplate.delete("https://fakestoreapi.com/products/" + id);
    }




    @Override
    public Page<Product> getAllProducts(int pageNumber, int sizeOfPage, String sortBy, String sortOrder) {

//        List<Product> products = new ArrayList<>();
//
//            FakeStoreProductDto  xyz[] = restTemplate.getForObject(
//                    "https://fakestoreapi.com/products",
//                    FakeStoreProductDto[].class                //List.class cannot be used here
//                                                               //because->list will use List<HashMap internally and array.class will use array of String items whic is needed.
//            );
//
//            for(FakeStoreProductDto fakeStoreProductDto : xyz){
//
//                products.add(convertFakeStoreProductToProduct(fakeStoreProductDto));
//
//            }
//
//            return  products;

        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {

    FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

    fakeStoreProductDto.setId(id);
    fakeStoreProductDto.setTitle(product.getTitle());
    fakeStoreProductDto.setDescription(product.getDescription());
    fakeStoreProductDto.setPrice(product.getPrice());
    fakeStoreProductDto.setCategory(product.getCategory().getName());
    fakeStoreProductDto.setImage(product.getImageURL());


        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor( FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto responseDto = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.POST, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(responseDto);


    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {

            return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }


}
