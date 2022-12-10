package com.app.myconame.service;

import java.util.List;

import com.app.myconame.entity.Contact;

public interface IContactService {

	Integer saveContact(Contact c);
	List<Contact> getAllContacts();
	Contact getContactById(Integer id);
	void deleteContact(Integer id);
	void updateContact(Contact c);
}
