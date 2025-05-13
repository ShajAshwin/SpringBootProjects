package com.example.AddressBook.exeption;

public class NoSuchCustomerExistsException extends RuntimeException{

    public NoSuchCustomerExistsException(){
    }

    public NoSuchCustomerExistsException(String message) {
        super(message);
    }
}
