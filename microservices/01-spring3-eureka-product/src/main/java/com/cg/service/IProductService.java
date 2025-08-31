package com.cg.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.entity.Product;
import com.cg.exception.ResourceNotFoundException;

public interface  IProductService {
	List<com.cg.entity.Product> getProductsFromDatabase();
	Optional<com.cg.entity.Product> getProductById(int id);
   com.cg.entity.Product createProduct( com.cg.entity.Product product);
   ResponseEntity<com.cg.entity.Product> updateProduct(Integer productId, Product productDetails) throws ResourceNotFoundException;
   Map<String, Boolean> deleteProduct (Integer productId) throws  ResourceNotFoundException;

}


