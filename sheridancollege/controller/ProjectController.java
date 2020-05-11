package ca.sheridancollege.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.entities.Employee;
import ca.sheridancollege.entities.Project;
import ca.sheridancollege.respository.EmployeeRepository;
import ca.sheridancollege.respository.ProjectRepository;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	//must provide access modifyer in this case private 
	private ProjectRepository proRepo;
	
	@Autowired 
	private EmployeeRepository empRepo;
	
	//the purprose of this method is to display all the 
	// project s
	@GetMapping 
	public String diplayProjects(Model model) { 
		
		//find all the projects 
		List <Project> project = proRepo.findAll(); 
		//add to the model attributes 
		model.addAttribute("project", project); 
		
		return "list-project.html";
	}
	
	
	@RequestMapping("/new")
	public String displayProjectForm(Model model) { 
		
		//here we initialize an empty project
		// that is why we needed empty contructor 
		// we will be created object in html form. 
		// bind empty project to the form.
		Project aProject = new Project(); 
		//key = project (how it it is binded in thymleaf form action 
		// value = aProject = what is aProject() 
		// we need to create initialize project 
		// Project aProject = new Project() 
		model.addAttribute("project", aProject);
		
		//find all employees in form of list 
		List <Employee> employees = empRepo.findAll();
		// add to the model in thymleaf we mentioned allEmployees 
		// and employees in the list of employees 
		model.addAttribute("allEmployees", employees);

		
		return "new-project.html";
		
	}
	
	@PostMapping("/save") 
	//handles saving to db this order must be followed otherwise it wil give error
	//request param for employees bc in the table projectId is null and not being updated. 
	public String createProject(Project project,/* @RequestParam List<Long> employees*/ Model model) { 
		
		//save project instance 
		proRepo.save(project);
		
		//iterate over the employees
		// multiple id will passed through request 
//		Iterable <Employee> chosenEmployee = empRepo.findAllById(employees);
//		
//		for (Employee emp : chosenEmployee) { 
//			emp.setTheProject(project);
			//empRepo.save(emp);
		//}
		//redirect to prevent duplicate submission
		// /new controller already returns new-project-html
		//return "redirect:/projects/new";
		return "redirect:/projects";
		
	}

}
