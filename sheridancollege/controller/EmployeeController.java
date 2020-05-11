package ca.sheridancollege.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.sheridancollege.entities.Employee;
import ca.sheridancollege.entities.Project;
import ca.sheridancollege.respository.EmployeeRepository;

@Controller 
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository empRepo; 
	
	//by default it will go to localhost:8080/employees 
	//the purpose of this method is to display 
	// list of the all the employees 
	// 
	@GetMapping
	public String displayEmployees(Model model) { 
		// put the employee object into a list 
		// then used the employee repo to find all 
		// and added to the model 
        	List <Employee> employee = empRepo.findAll(); 
			model.addAttribute("employee", employee); 
			
			return "list-employee.html";
		}
	
	
	@RequestMapping("/new")
	public String displayEmployeeForm(Model model) { 
		
	
		Employee aEmployee = new Employee(); 

		model.addAttribute("employee", aEmployee);
		return "new-employee.html";
		
	}
	
	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) { 
		empRepo.save(employee); 
		
		return "redirect:/employees/new";
}

}