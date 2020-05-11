package ca.sheridancollege.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.entities.UserAccount;
import ca.sheridancollege.respository.UserAccountRepository;

@Controller
public class SecurityController {
	
	//we want to encrypt the password 
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired 
	UserAccountRepository accountRepo;

	//register
	@GetMapping("/register")
	public String register(Model model) { 
		// already made an entity for userAccount
		// get everuthong from userAccount entity
		UserAccount userAccount = new UserAccount();
		//add it to the model - we calling it userAccount(in blue) and the object 
		// we instanted from line 16 userAccount 
		model.addAttribute("userAccount", userAccount);
		return "register";
		
	}
	
	//save
	
	@PostMapping("/register/save")
	public String saveUser(Model model, UserAccount user) { 
		//get the user apssword from the form 
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		accountRepo.save(user);
		//
		return "redirect:/";
}
}
