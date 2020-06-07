package com.mitre.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.data.UserAccountContactResponse;
import com.client.CallRestService;

@RestController
public class UserController {
	
	@Autowired
	CallRestService callRestService;
	
	@GetMapping("/getUserAccounts/{id}")
	@ResponseBody
	public UserAccountContactResponse getUserAccounts(@PathVariable("id") int id) {
		UserAccountContactResponse userAccountResponse = callRestService.getUserAccountForId(id);
		return userAccountResponse;
	}
	
}
