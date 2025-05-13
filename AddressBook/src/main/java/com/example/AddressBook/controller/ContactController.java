package com.example.AddressBook.controller;

import com.example.AddressBook.dto.ContactDTO;
import com.example.AddressBook.entity.Contact;
import com.example.AddressBook.exeption.ContactAlreadyExistsException;
import com.example.AddressBook.exeption.ErrorResponse;
import com.example.AddressBook.service.ContactService;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("addressBook")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping("/addContact")
    @ResponseStatus
    public ResponseEntity<String> addContact(@RequestBody @Valid ContactDTO contactDTO){
        return new ResponseEntity<>(contactService.addContact(contactDTO), HttpStatus.CREATED);
    }

    @GetMapping("/allContacts")
    public ResponseEntity<List<Contact>> getAllContacts(){
        return new ResponseEntity<>(contactService.getAllContacts(),HttpStatus.FOUND);
    }

    @GetMapping("/contact/{address}")
    public ResponseEntity<List<Contact>> getContactByAddress(@PathVariable("address") String address){
        return new ResponseEntity<>(contactService.getContactByAddress(address),HttpStatus.FOUND);
    }

    @PutMapping("/updateContact/{id}")
    public ResponseEntity<String> updateContact(@RequestBody Contact contact, @PathVariable("id") int id){
        contactService.updateContactById(contact,id);
        return new ResponseEntity<>("Record updated Successfully",HttpStatus.OK);
    }

    @DeleteMapping("/deleteContact/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id){
        contactService.deleteById(id);
        return new ResponseEntity<>("Contact Deleted successfully " + id,HttpStatus.ACCEPTED) ;
    }


}

