package edu.uptc.iShop2.exceptions;

public class NegativePriceException extends Exception{
    public NegativePriceException(String message){
        super(message);
    }
}
