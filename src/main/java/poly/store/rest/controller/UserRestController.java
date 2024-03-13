/**
 * @(#)EmployeeRestController.java 2021/09/10.
 * 
 * Copyright(C) 2021 by PHOENIX TEAM.
 * 
 * Last_Update 2021/09/10.
 * Version 1.00.
 */
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

import poly.store.entity.User;
import poly.store.model.ChangePassModel;
import poly.store.model.EmployeeForm;
import poly.store.model.InformationModel;
import poly.store.service.UserService;

/**
 * Class cung cap cac dich vu rest api cho bang employee
 * 
 * @author khoa-ph
 * @version 1.00
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/user")
public class UserRestController {
	@Autowired
	UserService userService;
	
	@GetMapping("{email}")
	public User getUserByEmail(@PathVariable("email") String email) {
		return userService.findUserByEmail(email);
	}
	
	@GetMapping()
	public List<User> getAllUser() {
		return userService.findAllUserAnable();
	}
	
	@GetMapping("/account")
	public InformationModel getUserAccount() {
		return userService.getUserAccount();
	}
	
	@PostMapping
	public User create(@RequestBody User user) {
		return userService.create(user);
	}
	
	@PutMapping("/account/update")
	public InformationModel update(@RequestBody InformationModel informationModel) {
		return userService.update(informationModel);
	}
	
	@PutMapping("/account/change-password")
	public ChangePassModel changePass(@RequestBody ChangePassModel changePassModel) {
		return userService.updatePass(changePassModel);
	}
	
	@GetMapping("/list")
	public List<User> getAllUserNotRoleAdmin() {
		return userService.findAllUserNotRoleAdmin();
	}
	
	@PostMapping("/form")
	public InformationModel createUser(@RequestBody InformationModel informationModel) {
		return userService.createUser(informationModel);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
	}
	
	@GetMapping("/update/{id}")
	public InformationModel getOneUserById(@PathVariable("id") Integer id) {
		return userService.getOneUserById(id);
	}
	
	@PutMapping("/form/{id}")
	public InformationModel update(@PathVariable("id") Integer id, @RequestBody InformationModel informationModel) {
		return userService.updateUser(informationModel, id);
	}
}
