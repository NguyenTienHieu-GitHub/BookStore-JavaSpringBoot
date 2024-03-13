package poly.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.Contact;
import poly.store.model.ContactModel;
import poly.store.service.ContactService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/contact")
public class ContactRestController {
	@Autowired
	ContactService contactService;
	
	@GetMapping
	public List<Contact> getAll(){
		return contactService.getListContactChecked();
	}
	
	@PostMapping("/form")
	public ContactModel create(@RequestBody ContactModel contactModel) {
		return contactService.createContact(contactModel);
	}
	
	@GetMapping("/pending")
	public List<Contact> getListContactPending(){
		return contactService.getListContactPending();
	}
	
	@GetMapping("/pending/{id}")
	public Contact getContactByContactId(@PathVariable("id") Integer id) {
		return contactService.getContactByContactId(id);
	}
	
	@PutMapping("/form/approve/{id}")
	public void approve(@PathVariable("id") Integer id) {
		contactService.approveContact(id);
	}
	
	@DeleteMapping("/form/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		contactService.delete(id);
	}
	
	@GetMapping("/approved")
	public List<Contact> getListContactChecked(){
		return contactService.getListContactChecked();
	}
}
