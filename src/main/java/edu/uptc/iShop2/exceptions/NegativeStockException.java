package edu.uptc.iShop2.exceptions;

public class NegativeStockException extends Exception{

    public NegativeStockException(String message){
        super(message);
    }
}
