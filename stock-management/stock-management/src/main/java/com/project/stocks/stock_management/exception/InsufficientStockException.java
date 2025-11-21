package com.project.stocks.stock_management.exception;

public class InsufficientStockException extends RuntimeException{
    public InsufficientStockException(String msg){
        super(msg);
    }
}
