package com.cg.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.config.FeignConfig;
import com.cg.dto.Product;

 // shared entity or create DTO

//@FeignClient(name = "product-service", url = "http://localhost:8013/papi") 
@FeignClient(name = "product-service", url = "http://localhost:8013/papi", configuration = FeignConfig.class)
public interface ProductClient {

    @GetMapping("/products")
    List<Product> getAllProducts();
    
    
    @GetMapping("/products/db/{id}")
    Product getProductById(@PathVariable("id") int id);
    
    
}
