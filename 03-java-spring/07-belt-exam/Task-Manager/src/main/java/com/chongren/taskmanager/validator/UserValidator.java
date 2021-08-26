package com.chongren.taskmanager.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.chongren.taskmanager.model.User;
import com.chongren.taskmanager.repository.UserRepository;

@Component
public class UserValidator {
	@Autowired
	public UserRepository uRepo;
	
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		if(!user.getPassword().equals(user.getConfirmPassword())) {               //Confirm pw is not match
			errors.rejectValue("password", "Match", "PW do not match!!!");
		}
		
		if(this.uRepo.existsByEmail(user.getEmail())) {                //Check if email has been registered
			errors.rejectValue("email", "unique", "This email has already been registered");
		}
		
		if(user.getFirstName().equals("Matt")) {                  //Personalize rule
			errors.rejectValue("firstName", "noMattAllowed", "Sorry, we are not accepting Matt in this exam");
		}
		
		
	}

}
