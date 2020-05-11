package ca.sheridancollege.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.entities.Employee;
import ca.sheridancollege.respository.EmployeeRepository;

@RestController
@RequestMapping("app-api/employee")
public class EmployeeApiController {

	@Autowired 
	EmployeeRepository empRepo;
	
	//API to return details of all employees 
	@GetMapping 
	public Iterable<Employee> getEmployee() { 
		return empRepo.findAll();
	}
	
	// to find employee by id get end points.
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) { 
		return empRepo.findById(id).get();
	}
	
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody Employee employee) { 
		return empRepo.save(employee); 
     }
	
	@PutMapping(path="{/id}",consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee update (@RequestBody Employee employee) {
		return empRepo.save(employee);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable("id") Long id ) { 
		empRepo.deleteById(id);
	}

	
}
