package com.app.myconame.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.myconame.entity.Contact;
import com.app.myconame.exception.ContactNotFoundException;
import com.app.myconame.repository.ContactRepository;

@Service
@Transactional
public class ContactServiceImpl implements IContactService {
	
	@Autowired
	private ContactRepository repo;
	
	public Integer saveContact(Contact c) {
		return repo.save(c).getId();
	}

	public List<Contact> getAllContacts() {
		return repo.findAll();
	}

	public Contact getContactById(Integer id) {
		return repo.findById(id)
				.orElseThrow(() -> new ContactNotFoundException("CONTACT '"+ id +"' DOES NOT EXIST"));
	}

	public void deleteContact(Integer id) {
		repo.delete(getContactById(id));
	}

	public void updateContact(Contact c) {
		if(!repo.existsById(c.getId()) || c.getId()==null) {
			throw new ContactNotFoundException("CONTACT '"+c.getId()+"' DOES NOT EXIST");
		} else {
			repo.save(c);
		}
	}

}
