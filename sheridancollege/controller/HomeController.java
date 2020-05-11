package ca.sheridancollege.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.entities.Employee;
import ca.sheridancollege.entities.Project;
import ca.sheridancollege.respository.EmployeeRepository;
import ca.sheridancollege.respository.ProjectRepository;

@Controller

//landing page
public class HomeController {
	
	//needed this to get methods such as findAll 
	@Autowired
	private ProjectRepository proRepo;
	
	@Autowired
	private EmployeeRepository empRepo; 
	

	@GetMapping("/")
	public String displayHome(Model model) { 
		List <Project> projects = proRepo.findAll();
		List <Employee> employees = empRepo.findAll();
		
		//we need this for each in the home.html 
		// we are iterating over projectList 
		// example for aProject in projectList
		model.addAttribute("projectList", projects);
		model.addAttribute("employeeList", employees );
		return "home";
	}

}
