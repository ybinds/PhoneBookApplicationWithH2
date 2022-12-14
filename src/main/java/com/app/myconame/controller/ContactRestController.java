package com.app.myconame.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/v1/api/contact")
public class ContactRestController {

	@Autowired
	private IContactService service;
	
	//1. create Contact
	@PostMapping("/")
	private ResponseEntity<String> createContact(
			@RequestBody @Valid Contact c){
			Integer id = service.saveContact(c);
		return new ResponseEntity<String>("Contact '"+id+"' saved successfully", HttpStatus.CREATED);
	}
	
	//2. get all Contacts
	@GetMapping("/contacts")
	private ResponseEntity<List<Contact>> getAllContacts(){
		return ResponseEntity.ok(service.getAllContacts());
	}

	//3. get one contact
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

	//4. update one contact
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

	//5. delete one contact
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
