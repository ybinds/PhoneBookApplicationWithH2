package com.app.myconame.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.myconame.entity.Contact;

public interface IContactService {

	Integer saveContact(Contact c);
	List<Contact> getAllContacts();
	Page<Contact> getAllContacts(Pageable p);
	List<Contact> getAllContacts(String word);
	Contact getContactById(Integer id);
	void deleteContact(Integer id);
	void updateContact(Contact c);
}
