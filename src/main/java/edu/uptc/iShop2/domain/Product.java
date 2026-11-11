package edu.uptc.iShop2.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor 
@Data 
@NoArgsConstructor
@Entity 
public class Product {

    @Id
    private UUID id;

    private String name;

    private Float price;

    private Integer stock;


    
}
