package com.example.AddressBook.service;

import com.example.AddressBook.dao.ContactRepository;
import com.example.AddressBook.dto.ContactDTO;
import com.example.AddressBook.entity.Contact;
import com.example.AddressBook.exeption.ContactAlreadyExistsException;
import com.example.AddressBook.exeption.NoSuchCustomerExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ContactServiceIMP implements ContactService {

    @Autowired
    ContactRepository repository;

    @Override
    public String addContact(ContactDTO contactDTO) {
        Contact contact = new Contact(
                contactDTO.getName(),
                contactDTO.getMobileNumber(),
                contactDTO.getEmailID(),
                contactDTO.getAddress()
        );
        Contact existingContact = (Contact) repository.findByName(contact.getName()).orElse(null);

        if (existingContact == null) {
            repository.save(contact);
            return "Contact added Successfully!! ";
        } else throw new ContactAlreadyExistsException("Contact already exists!!");

    }


    @Override
    public List<Contact> getAllContacts() {
        List<Contact> allContact = repository.findAll();
        if(allContact.size() > 0) return allContact;
        else throw new NoSuchCustomerExistsException("No Contact Found");
    }


    @Override
    public List<Contact> getContactByAddress(String address) {
        List<Contact> listByAddress = repository.findByAddress(address);

        if (listByAddress.size()>0){
            return listByAddress;
        }else {
            throw new NoSuchCustomerExistsException("No Contact Found");
        }


    }

    @Override
    public void updateContactById(Contact contact, int id) {
        Contact updateContact = repository.findById(id)
                .orElseThrow(()-> new NoSuchCustomerExistsException("No Such Customer exists!!"));

        updateContact.setName(contact.getName());
        updateContact.setMobile(contact.getMobile());
        updateContact.setEmail(contact.getEmail());
        updateContact.setAddress(contact.getAddress());

         repository.save(updateContact);
    }

    @Override
    public void deleteById(int id) {
        Contact contact = repository.findById(id)
                .orElseThrow(() -> new NoSuchCustomerExistsException("No customer with ID =  '\n'"+ repository.findById(id).get()));
        repository.deleteById(id);
    }


}
