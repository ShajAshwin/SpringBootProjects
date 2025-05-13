package com.example.AddressBook.exeption;

public class ContactAlreadyExistsException extends RuntimeException{

    public String message;

    public ContactAlreadyExistsException() {

    }

    public ContactAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
