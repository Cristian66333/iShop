package edu.uptc.iShop2.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductDTO (String id,
                          @NotNull
                          String name,
                          @Min(value = 0, message = "El precio no puede ser 0")
                          Float price,
                          @Min(value = 1, message = "El stock no puede ser menor a 1")
                          Integer stock){}
