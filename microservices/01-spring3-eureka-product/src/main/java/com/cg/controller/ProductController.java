package com.cg.controller;
//-----------TESTING----------https://springframework.guru/testing-spring-boot-restful-services/ 
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Product;
import com.cg.exception.ResourceNotFoundException;
import com.cg.service.IProductService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/papi")
	public class ProductController {
		@Autowired
		IProductService productService ;
	
	   
	    //@GetMapping(path = "/products", produces = {MediaType.APPLICATION_XML_VALUE})
	    @GetMapping(path = "/products", produces = {MediaType.APPLICATION_JSON_VALUE})
	    List<Product> products() {
	        return productService.getProductsFromDatabase();
	    }
	    
		//Demo of @PathVariable
		@GetMapping("/products/db/{id}")
		Product findByProductIdFromDBWithException(@PathVariable int id) throws ResourceNotFoundException
		{	Product product = productService.getProductById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
           System.out.println(id);
        return product;	
		}
		
		
		//Demo of @RequestParam:  http://localhost:9090/products?id=1
				@GetMapping("/products/{id}")
				Product findByProductIdUsingrequestParam(@RequestParam int id) throws ResourceNotFoundException
				{	Product product = productService.getProductById(id)
		        		.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
		           System.out.println(id);
		        return product;	
				}
		
	    @PostMapping("/products")
	    public Product createEmployee(@Valid @RequestBody Product newProduct) {
	        return productService.createProduct(newProduct);
	    }
	    
	    @PutMapping("/products/{id}")
	    public ResponseEntity<Product> updateEmployee(@PathVariable(value = "id")  Integer productId, @Valid @RequestBody Product newProduct) throws ResourceNotFoundException 
	    {
	        return productService.updateProduct(productId, newProduct);
	    }
	    

	    @DeleteMapping("/products/{id}")
	    public Map<String,Boolean> deleteProduct(@PathVariable (value="id") Integer productId) throws ResourceNotFoundException
	    {
	    	return productService.deleteProduct(productId);
	    }
	     
}