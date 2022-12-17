package com.app.myconame.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		c.setActive(true);
		return repo.save(c).getId();
	}

	public List<Contact> getAllContacts() {
		return repo.findAllByActive(true);
	}
	
	public Page<Contact> getAllContacts(Pageable p) {
		return repo.findAll(p);
	}

	public List<Contact> getAllContacts(String word) {
		return repo.getAllContactsByWord(word);
	}
	
	public Contact getContactById(Integer id) {
		return repo.findById(id)
				.orElseThrow(() -> new ContactNotFoundException("CONTACT '"+ id +"' DOES NOT EXIST"));
	}

	public void deleteContact(Integer id) {
		//repo.delete(getContactById(id));
		Contact c = getContactById(id);
		if(c != null) { 
			c.setActive(false);
			repo.save(c);
		}	
	}

	public void updateContact(Contact c) {
		if(!repo.existsById(c.getId()) || c.getId()==null) {
			throw new ContactNotFoundException("CONTACT '"+c.getId()+"' DOES NOT EXIST");
		} else {
			repo.save(c);
		}
	}

}
