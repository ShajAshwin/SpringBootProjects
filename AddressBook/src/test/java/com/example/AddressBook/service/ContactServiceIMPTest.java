package com.example.AddressBook.service;

import com.example.AddressBook.dao.ContactRepository;
import com.example.AddressBook.dto.ContactDTO;
import com.example.AddressBook.entity.Contact;
import com.example.AddressBook.exeption.ContactAlreadyExistsException;
import com.example.AddressBook.exeption.NoSuchCustomerExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ContactServiceIMPTest {

    @Mock
    private ContactRepository repository;

    @InjectMocks
    private ContactServiceIMP service;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("JUnit test for saveContact method")
    @Test
    void addContact() {

        ContactDTO contactDTO = ContactDTO.build("John", 10L, "john@example.com", "Chennai");
        ContactDTO negativeDto = ContactDTO.build("George", 10L, "george@example.com", "Bangalore");

        Contact positiveContact = new Contact(contactDTO.getName(), contactDTO.getMobileNumber(), contactDTO.getEmailID(), contactDTO.getAddress());
        Contact negativeContact = new Contact(negativeDto.getName(),negativeDto.getMobileNumber(),negativeDto.getEmailID(),negativeDto.getAddress());
        repository.save(positiveContact);

        when(repository.findByName(positiveContact.getName())).thenReturn(Optional.empty());
        when(repository.findByName(negativeContact.getName())).thenReturn(Optional.of(negativeContact));
        when(repository.save(positiveContact)).thenReturn(positiveContact);

        String positiveResult = service.addContact(contactDTO);
        ContactAlreadyExistsException ex = assertThrows(ContactAlreadyExistsException.class,()->service.addContact(negativeDto));

        assertEquals("Contact added Successfully!! ", positiveResult);
        assertEquals("Contact already exists!!",ex.getMessage());

    }

    @Test
    void getAllContacts() {

        Contact contact1 = new Contact("John",10l,"john@gmail.com","Chennai");
        Contact contact2 = new Contact("George",10l,"george@gmail.com","Bangalore");

        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        contacts.add(contact2);

        when(repository.findAll()).thenReturn(contacts);


        List<Contact> allContacts = service.getAllContacts();

        assertEquals(allContacts,contacts);

    }

    @Test
    void getContactByAddress() {

        Contact contact1 = new Contact("John",10l,"john@gmail.com","Chennai");
        Contact contact2 = new Contact("George",10l,"george@gmail.com","Bangalore");

        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        List<Contact> emptyList = new ArrayList<>();

        when(repository.findByAddress("Chennai")).thenReturn(contacts);

        when(repository.findByAddress("InvalidAddress")).thenReturn(new ArrayList<>());
        NoSuchCustomerExistsException ex = assertThrows(NoSuchCustomerExistsException.class,()-> service.getContactByAddress("InvalidAddress"));

        List<Contact> chennai = service.getContactByAddress("Chennai");

        assertEquals(chennai,contacts);
        assertEquals("No Contact Found",ex.getMessage());


    }

    @Test
    void updateContactById() {

        Contact originalContact = new Contact("John Doe", 8234567890l, "john@example.com", "Chennai");
        Contact updatedContact = new Contact("Updated Name", 9876543210l, "updated@example.com", "New Address");

        when(repository.findById(1)).thenReturn(Optional.of(originalContact));

        service.updateContactById(updatedContact, 1);

        verify(repository).save(updatedContact);
    }


    @Test
    void deleteById() {
        Contact contactToDelete = new Contact("John Doe", 1234567890l, "john@example.com", "Chennai");
        when(repository.findById(1)).thenReturn(Optional.of(contactToDelete));

        service.deleteById(1);

        verify(repository).deleteById(1);
    }

    @Test
    public void testDeleteByIdContactNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.deleteById(1));
    }


}