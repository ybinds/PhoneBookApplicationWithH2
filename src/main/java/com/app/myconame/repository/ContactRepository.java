package com.app.myconame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.myconame.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
