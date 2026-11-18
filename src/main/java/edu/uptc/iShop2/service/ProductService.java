package edu.uptc.iShop2.service;


import edu.uptc.iShop2.domain.repository.ProductRepository;
import edu.uptc.iShop2.dtos.ProductDTO;
import edu.uptc.iShop2.exceptions.NegativePriceException;
import edu.uptc.iShop2.exceptions.NegativeStockException;
import edu.uptc.iShop2.tools.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
