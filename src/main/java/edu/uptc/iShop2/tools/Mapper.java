package edu.uptc.iShop2.tools;

import edu.uptc.iShop2.domain.Product;
import edu.uptc.iShop2.dtos.ProductDTO;

import java.util.UUID;


public class Mapper {
    public Product productDtoToDomain(ProductDTO dto){
        if (dto.id().equals("")){
            return new Product(null, dto.name(), dto.price(),dto.stock());
        }
        return new Product(UUID.fromString(dto.id()), dto.name(), dto.price(),dto.stock());
    }

    public ProductDTO productToProductDTO(Product product){
        return new ProductDTO(product.getId().toString(),
                product.getName(),
                product.getPrice(),
                product.getStock());
    }
}
