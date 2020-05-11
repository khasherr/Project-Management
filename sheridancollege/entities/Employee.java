package ca.sheridancollege.entities;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Employee {
	
	@Id
	// generationType.Identity if you want to generate data from data sqlfile
	// squence if you have schema file for batch update and processing
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator ="employee_id")
	private long employeeId; 
	private String firstName;
	private String lastName; 
	private String email; 
	
	//many employees can be assigned to one project 
	//cascade means if something that parent entities 
	// we want the children entities follow same rule 
	// example if merge project - the employee shoudl
	// merge as well. Remove if you remove project all
	// emp associatdd iwth project will remove as well.
	// persist == save in db 
	//cascading rule takes place in children not parent !
	//@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST},  
			//fetch lazy means load project and make sperate call to load employee
			// fetch eager = slower app down
			//fetch = FetchType.LAZY)
	   //  	//@JoinColumn(name="project_Id")

	
	//many employee can be associated with many projects and vice versa
	@ManyToMany(cascade= {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch=FetchType.LAZY)
	@JoinTable(name="project_employee", 
	joinColumns = @JoinColumn(name ="employee_id"), 
	inverseJoinColumns=@JoinColumn(name="project_id")
	)
	
	//since its a many to many relationship 
	// it is a list of projects !
	
	@JsonIgnore // ignores the theProjects field for serialization if you dont do this 
	// the data from api will involve data from project, projects_id and employee table 
	private List<Project> theProjects; 

}
