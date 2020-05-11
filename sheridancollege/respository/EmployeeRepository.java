package ca.sheridancollege.respository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.sheridancollege.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	// commented out for sake of EmployeeAPI controller so we can use the Iterable 
	@Override 
	List<Employee> findAll(); 
}
