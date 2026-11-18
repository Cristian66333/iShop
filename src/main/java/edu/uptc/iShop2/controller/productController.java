package edu.uptc.iShop2.controller;

import edu.uptc.iShop2.dtos.ProductDTO;
import edu.uptc.iShop2.exceptions.NegativePriceException;
import edu.uptc.iShop2.exceptions.NegativeStockException;
import edu.uptc.iShop2.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class productController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO product){
        try{
            ProductDTO createdProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        }catch (NegativeStockException nst){
            return ResponseEntity.badRequest().build();
        } catch (NegativePriceException npt){
            return ResponseEntity.badRequest().build();
        }

    }

}
