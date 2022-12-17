package com.app.myconame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.myconame.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

	@Query("SELECT c FROM Contact c WHERE c.name LIKE %:word% OR c.email LIKE %:word% OR c.phoneNumber LIKE %:word%")
	List<Contact> getAllContactsByWord(String word);
	
	List<Contact> findAllByActive(Boolean active);
}
