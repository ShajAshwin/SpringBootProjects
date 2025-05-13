package com.example.AddressBook.service;

import com.example.AddressBook.dto.ContactDTO;
import com.example.AddressBook.entity.Contact;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContactService {

    public String addContact(ContactDTO contactDTO);
    public List<Contact> getAllContacts();
    public List<Contact> getContactByAddress(String address);
    public void updateContactById(Contact contact, int id);
    public void deleteById(int id);


}
