package edu.uptc.iShop2.service;


import edu.uptc.iShop2.domain.Product;
import edu.uptc.iShop2.domain.repository.ProductRepository;
import edu.uptc.iShop2.dtos.ProductDTO;
import edu.uptc.iShop2.exceptions.NegativePriceException;
import edu.uptc.iShop2.exceptions.NegativeStockException;
import edu.uptc.iShop2.tools.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    private Mapper mapper = new Mapper();

    public ProductDTO createProduct(ProductDTO product) throws NegativeStockException, NegativePriceException{
        if (product.stock()<=0){
            throw new NegativeStockException("El stock no pude ser negativo al crear el producto");
        }
        if (product.price()<=0){
            throw new NegativePriceException("El precio dl producto no pud ser negativo");
        }


        return mapper.productToProductDTO(repository.save(mapper.productDtoToDomain(product)));
    }

    public List<Product> getProducts(){
        return repository.findAll();
    }

    public ProductDTO getProductByID(UUID id){
        Product foundProduct = repository.getProductById(id);

        return new ProductDTO(foundProduct.getId()+"", foundProduct.getName(), foundProduct.getPrice(), foundProduct.getStock());
    }

    public ProductDTO updateProduct(UUID id, Product product) throws Exception{
        var optionalFoundProduct = repository.findById(id);
        if (optionalFoundProduct.isPresent()){
            Product foundProduct = optionalFoundProduct.get();
            foundProduct.setName(product.getName());
            foundProduct.setPrice(product.getPrice());
            foundProduct.setStock(product.getStock());
            repository.save(foundProduct);
            return new ProductDTO(foundProduct.getId()+"", foundProduct.getName(),foundProduct.getPrice(),foundProduct.getStock());
        }
        throw new Exception("El objeto que se quiere modificar no existe");

    }
    public ProductDTO partialUpdateProduct(UUID id, Product data) throws Exception{
        var optionalFoundProduct = repository.findById(id);
        if (optionalFoundProduct.isPresent()){
            Product foundProduct = optionalFoundProduct.get();
            if (data.getName()!=null){
                foundProduct.setName(data.getName());
            }
            if( data.getPrice() != null){
                foundProduct.setPrice(data.getPrice());
            }
            if (data.getStock()!= null){
                foundProduct.setStock(data.getStock());
            }
            repository.save(foundProduct);
            return new ProductDTO(foundProduct.getId()+"", foundProduct.getName(),foundProduct.getPrice(),foundProduct.getStock());
        }
        throw new Exception("El objeto que se quiere modificar no existe");

    }

    public ProductDTO deleteProduct(UUID id){
        Product deleteProduct = repository.findById(id).get();

        repository.delete(deleteProduct);

        return new ProductDTO(deleteProduct.getId().toString(), deleteProduct.getName(), deleteProduct.getPrice(), deleteProduct.getStock());


    }

}
