package com.example.AddressBook.dao;


import com.example.AddressBook.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findByAddress(String address);

    Optional<Object> findByName(String name);
}
