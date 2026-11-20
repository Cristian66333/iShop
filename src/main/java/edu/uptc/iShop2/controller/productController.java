package edu.uptc.iShop2.controller;

import edu.uptc.iShop2.domain.Product;
import edu.uptc.iShop2.dtos.ProductDTO;
import edu.uptc.iShop2.exceptions.NegativePriceException;
import edu.uptc.iShop2.exceptions.NegativeStockException;
import edu.uptc.iShop2.service.ProductService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
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

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductByID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @RequestBody Product product){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, product));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> partialUpdateProduct(@PathVariable UUID id, @RequestBody Product data){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.partialUpdateProduct(id, data));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(id));
    }


}
