package com.example.AddressBook.controller;

import com.example.AddressBook.dao.ContactRepository;
import com.example.AddressBook.dto.ContactDTO;
import com.example.AddressBook.entity.Contact;
import com.example.AddressBook.exeption.ContactAlreadyExistsException;
import com.example.AddressBook.service.ContactServiceIMP;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ContactControllerTest {

    @Mock
    ContactServiceIMP contactService;

    @Mock
    ContactRepository repository;

    @InjectMocks
    ContactController contactController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addContactPositiveCase() {
            ContactDTO contactDTO = new ContactDTO("John", 10L, "john@example.com", "Chennai");


            when(contactService.addContact(contactDTO)).thenReturn("Contact added Successfully!!");
            ResponseEntity<String> responseEntity = contactController.addContact(contactDTO);
            assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
            assertEquals("Contact added Successfully!!", responseEntity.getBody());

    }

    @Test
    public void testGetAllContacts() {
        // Create a list of contacts for the test
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("John Doe", 1234567890l, "john@example.com", "Chennai"));

        when(contactService.getAllContacts()).thenReturn(contacts);

        ResponseEntity<List<Contact>> responseEntity = contactController.getAllContacts();

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(contacts, responseEntity.getBody());
    }

    @Test
    public void testGetContactByAddress() {

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("John Doe", 1234567890l, "john@example.com", "Chennai"));

        when(contactService.getContactByAddress("Chennai")).thenReturn(contacts);

        ResponseEntity<List<Contact>> responseEntity = contactController.getContactByAddress("Chennai");

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(contacts, responseEntity.getBody());
    }

    @Test
    public void testUpdateContact() {

    Contact contact = new Contact("John", 1234567890l, "john@example.com", "Chennai");
    int id = 1;

    ResponseEntity<String> responseEntity = contactController.updateContact(contact, id);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("Record updated Successfully", responseEntity.getBody());

    verify(contactService).updateContactById(contact, id);

}

    @Test
    public void testDeleteById() {
        int id = 1;
        ResponseEntity<String> responseEntity = contactController.deleteById(id);

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals("Contact Deleted successfully " + id, responseEntity.getBody());

        verify(contactService).deleteById(id);
    }

}