package com.app.myconame.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.myconame.entity.Contact;
import com.app.myconame.exception.ContactNotFoundException;
import com.app.myconame.service.IContactService;

@RestController
@CrossOrigin
@RequestMapping("/v1/api")
public class ContactRestController {

	@Autowired
	private IContactService service;
	
	//1. create Contact
	@PostMapping("/contact")
	private ResponseEntity<String> createContact(
			@RequestBody @Valid Contact c){
			Integer id = service.saveContact(c);
		return new ResponseEntity<String>("Contact '"+id+"' saved successfully", HttpStatus.CREATED);
	}
	
	//2. get all Contacts
	@GetMapping("/contacts")
	private ResponseEntity<List<Contact>> getAllContacts(
			@PageableDefault(page = 0, size = 2) Pageable pageable
			){
		return ResponseEntity.ok(service.getAllContacts());
	}

	//3. get all Contacts, page by page
	@GetMapping("/contactsPage")
	private ResponseEntity<Page<Contact>> getPagedContacts(
			@PageableDefault(page = 0, size = 2) Pageable pageable
			){
		return ResponseEntity.ok(service.getAllContacts(pageable));
	}
	
	//3. get all Contacts, page by page
	@GetMapping("/search/{word}")
	private ResponseEntity<List<Contact>> getMatchingContacts(
			@PathVariable("word") String word
			){
		return ResponseEntity.ok(service.getAllContacts(word));
	}
		
	//4. get one contact
	@GetMapping("/contact/{id}")
	private ResponseEntity<Contact> getOneContact(
			@PathVariable("id") Integer id){
		ResponseEntity<Contact> response = null;
		try {
			response = ResponseEntity.ok(service.getContactById(id));
		} catch(ContactNotFoundException cnfe) {
			cnfe.printStackTrace();
			throw cnfe;
		}
		return response;
	}

	//5. update one contact
	@PutMapping("/contact")
	private ResponseEntity<String> updateContact(
			@RequestBody @Valid Contact c){
		ResponseEntity<String> response = null;
		try {
			service.updateContact(c);
			response = ResponseEntity.ok("CONTACT '"+c.getId()+"' UPDATED SUCCESSFULLY");
		} catch(ContactNotFoundException cnfe) {
			cnfe.printStackTrace();
			throw cnfe;
		}
		return response;
	}

	//6. delete one contact
	@DeleteMapping("/contact/{id}")
	private ResponseEntity<String> deleteContact(
			@PathVariable("id") Integer id){
		ResponseEntity<String> response = null;
		try {
			service.deleteContact(id);
			response = ResponseEntity.ok("CONTACT '"+id+"' DELETED SUCCESSFULLY");
		} catch(ContactNotFoundException cnfe) {
			cnfe.printStackTrace();
			throw cnfe;
		}
		return response;
	}
}
